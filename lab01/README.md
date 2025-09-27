# LAB 01 - DATABASE MIGRATION

## Define

In software development, there are cases that we need to migrate from old to new database.

Requirements are:

- Not downtime. User must use system normally during the time we migrate database
- Emergency. Rollback-able. If something happens during database migration, such as writing to new database fail, we
  must rollback to the old database.
- Correctness. The old and new database must be the same, must be consistent.

## Technologies must know

### Debezium

A service that captures all row-level changes happen in database, so that application can respond to it.

### Kafka

A streaming platform, popular for its scalability and throughput. 
It is also a message queue, support async communication between services.

#### Kafka Connect

A service in Kafka ecosystem. It gets data from source, transform it, then put it to sink.

### Mysqldump
A tool to create a logical snapshot of MySQL database.

A logical snapshot is a set of SQL statements that can be used to recreate the database.

## Task
You must migrate database from a lab01_old_db container to lab01_new_db container.

The migration must satisfy all conditions listed in define.

About the database:
- Database name: luke
- Tables:
    - Transactions: 9 million rows

### Prepare lab data


## Migration DB ways

### Naive way

#### Analyze

When I was working at my first company, I need to migrate database MySQL that is running on old server to new server. 

The reason for this task is because of the restructure server and network in company, my team's services belongs to another network cluster, so my team needs to move database and service to a new network cluster.

What I do is a very naive way:
- Stop the service
- Dump the database using mysqldump
- Apply dump into a new server
- Start the service, using new database

This way is simple, but has a very big disadvantages, Downtime is large if database is large.

So why my boss accept this way. It's because my service is allowed to have a downtime. The DB is used for admin tools,
and it contains statistics data of a payment system, aggregated by day. Admin service listens for event change transaction in 
message queue, then update to corresponding statistics row. During the downtime, messages change transaction are still stored on
message queue, so I don't loss data. Not only that, after each day, I will perform a full scan of transaction in day on main payment
service, and correct the statistic data. So the correctness of statistic database is kept. Admin tools often be used by internal users
like POs, directors, etc, and I ask my boss for a downtime at 7pm, so nobody uses it at the time I migrate DB ^^. Summarize, the downtime
doesn't have big affect to the service and user.

Performance of mysqldump is based on some factors:
- Read/write throughput of disk.
- Network throughput if mysqldump client and mysql server is in 2 different servers.
- Size of database.

I don't remember exactly how much time I dump database in my first migration DB task, but I remember that it less than 1 minutes because:
- All read/write throughput of disk is used for mysqldump because I stop my service, so no read/write is performed on DB.
- Size of database is very small. My DB only stores statistics like revenue, number of transactions for each tuple (status, merchant, product, zone, day). 

Get back to the lab. First I need to know the size of database. I run this SQL command to get average size of each row.
```SQL
select TABLE_NAME, AVG_ROW_LENGTH from INFORMATION_SCHEMA.tables where TABLE_NAME = "transactions";
```
![Transaction average row size](../images/lab01/transaction_avg_size.png)

From this, I know that each row in table transactions have average size 335 bytes, result in size of database is 335 x 9M = 1.5 GB.

Why I need to stop service before dumping data. Because if any writes is performed during dumping, these writes are not included in 
dump result. When new database applies dump data, it doesn't have these new writes, result in data loss.

#### Stop the service
This is the first step in migrating db in naive way. You must ensure that no write is performed, otherwise these writes will be lost.

#### Dump old database
Before dumping, mysqldump requires PROCESS privilege. You can login to database as root, then use this SQL to get PROCESS privilege 
for account that is used for mysqldump.

```SQL
grant process on *.* to 'luke_luke'@'%'
```
I use account luke_luke for mysqldump, so I grant process privilege to this account. 

The mysqldump command is written in [mysql dump command](../script/mysql/dump_command.sh). Change the username that mysqldump use from my name (luke_luke)
to your name. Also, you can change the result file of mysqldump. Result file is the file that mysqldump put logical snapshot of database in.
Then you can execute this command
```mysqldump
### Working directory is in mysql folder
sh .\dump_command.sh
```

This is my result.

![](../images/lab01/dump_finish.png)

![](../images/lab01/old_dump_size.png)

As you can see, it takes me 42 seconds to dump the database, and the file size is approximately 1.5 GB.

#### Apply dump file
In this step, you apply a dump file into a new database.

In my first task of migration, I simply login to the new database server, execute mysqldump to the old database on it,
and apply the dump file.

In this lab, you can also do it. 
But because I create dump file on host, so I first need to copy dump file from host to new database container, then apply it.

You can copy dump file and apply it to new database container by running
```Copy and apply dump
## Current working directory is script/docker/database
sh .\copy_apply_dump_file.sh
```

In this script, I first copy dump file to /var/dump/old_database.sql. Then I copy apply_dump_file.sh to /var/dump and execute it.
apply_dump_file.sh simply apply dump file to the SQL server running on container.

The result of copy and apply is

![](../images/lab01/apply_dump.png)

You can see, it takes 28 seconds to copy dump file to new database container, and 122 seconds to apply dump file to new database server. 

#### Start service, use new database
Now, you simply start service and use new database.

### Reduce downtime way
You can see that, the downtime for naive way is a lot. My database size is only 1.5 GB, no network throughput need because I work
at localhost and all read/write throughput is used for migration, but it takes me 42 + 28 + 122 seconds to migrate. With large databases in TB units, 
network and disk throughput is not large, the downtime could be hours. How can we reduce it.


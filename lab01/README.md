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

## Migration DB ways

### Naive way

When I was working at my first company, I need to migrate database MySQL that is running on a server to another server. 

The reason for this task is because of the restructure server and network in company, my team's services belongs to another network cluster, so my team needs to move database and service to our network cluster.

What I do is a very naive way:
- Stop the service
- Dump the database using mysqldump
- Apply dump into a new server
- Start the service, using new database

This way is simple, but has a very big disadvantages:
- Downtime is large if database is large. 

Let's do some maths to compute mysqldump performances. 
- mysqldump creates a logical backup. It creates a backup of database as SQL statements like inserts, create table SQL statements.
- I assume that our database is 

Why I can do this way? Why my boss accept it.






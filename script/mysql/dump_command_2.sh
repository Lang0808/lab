## ---------- dump database, record binlog position ----------

## In order to dump, you must grant PROCESS privilege for luke_luke
## Login to database as root, then execute this SQL
## grant process on *.* to 'luke_luke'@'%';

START_TIME=$(date +%s)

## I change places of mysqldump because current sql dump in my computer is version 8.0.36
## 8.0.36 uses SHOW MASTER STATUS to get binlog position, which is deprecated in 8.4.6
"C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqldump.exe" \
  --host=localhost \
  --port=3307 \
  --user=luke_luke \
  --tables \
  --source-data \
  --result-file='C:\Users\MINH BAO\data\lab\lab01\dump\old_database_2.sql' \
  -p \
  luke

END_TIME=$(date +%s)

echo "Dump completed in $(($END_TIME - $START_TIME)) seconds"
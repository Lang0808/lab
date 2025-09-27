## In order to dump, you must grant PROCESS privilege for luke_luke
## Login to database as root, then execute this SQL
## grant process on *.* to 'luke_luke'@'%';

START_TIME=$(date +%s)

mysqldump \
  --host=localhost \
  --port=3307 \
  --user=luke_luke \
  --tables \
  --result-file='C:\Users\MINH BAO\data\lab\lab01\dump\old_database.sql' \
  -p \
  luke

END_TIME=$(date +%s)

echo "Dump completed in $(($END_TIME - $START_TIME)) seconds"
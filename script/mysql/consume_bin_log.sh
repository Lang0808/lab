START_POSITION=16624
BINLOG_FILE='binlog.000036'

START_TIME=$(date +%s)

mysqlbinlog \
  --start-position=${START_POSITION} \
  --read-from-remote-server \
  --host=localhost \
  --port=3307 \
  --user=luke_luke \
  -p \
  --result-file='C:\Users\MINH BAO\data\lab\lab01\dump\old_bin_log.sql' \
  ${BINLOG_FILE}

END_TIME=$(date +%s)

echo "Get bin log completed in $(($END_TIME - $START_TIME)) seconds"
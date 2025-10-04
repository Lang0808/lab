## Copy dump file from host to container dir in new database container
## First, you need to login new database container, and create directory that contains dump file if it not exist.

BIN_LOG='C:\Users\MINH BAO\data\lab\lab01\dump\old_bin_log.sql'
BASE_PATH='/var/dump'
CONTAINER_DIR="${BASE_PATH}/old_bin_log.sql"
APPLY_FILE_NAME='apply_bin_log.sh'
CONTAINER_DIR_APPLY_FILE="${BASE_PATH}/${APPLY_FILE_NAME}"

## Copy bin log to new database container
START_TIME=$(date +%s)
docker cp "${BIN_LOG}" lab01_new_db:"${CONTAINER_DIR}"
END_TIME=$(date +%s)
echo "Apply bin log to new database container completed in $(($END_TIME - $START_TIME)) seconds"


## Copy and set permission apply file by the way
START_TIME=$(date +%s)
docker cp apply_bin_log.sh lab01_new_db:"${CONTAINER_DIR_APPLY_FILE}"
docker exec lab01_new_db chmod 777 "/${CONTAINER_DIR_APPLY_FILE}"
docker exec lab01_new_db sh -c "cd ${BASE_PATH} && ./${APPLY_FILE_NAME}"
END_TIME=$(date +%s)
echo "Apply dump file to new database container completed in $(($END_TIME - $START_TIME)) seconds"

docker exec lab01_new_db rm "/${CONTAINER_DIR_APPLY_FILE}"
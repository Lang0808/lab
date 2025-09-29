docker stop kafka_connect || true
docker rm kafka_connect || true

PLUGIN_PATH='/usr/kafka/plugin'
JARS_PATH='/etc/kafka-connect/jars'
SECRET_PATH='/etc/kafka-connect/secrets'
SECRET_KAFKA_PATH='/etc/kafka/secrets'
DATA_KAFKA='/var/lib/kafka/data'

PROJECT_PLUGIN_PATH="$(pwd)/../../../config/kafka/connect/plugin"

docker volume create --name kafka_connect_jars || true
docker volume create --name kafka_connect_secrets || true
docker volume create --name kafka_secret1 || true
docker volume create --name kafka_connect_data || true

docker run -d \
  --name kafka_connect \
  -e CONNECT_CONFIG_STORAGE_TOPIC=kafka_connect_config \
  -e CONNECT_OFFSET_STORAGE_TOPIC=kafka_connect_offsets \
  -e CONNECT_STATUS_STORAGE_TOPIC=kafka_connect_status \
  -e CONNECT_GROUP_ID=connect \
  -e CONNECT_KEY_CONVERTER=org.apache.kafka.connect.json.JsonConverter \
  -e CONNECT_VALUE_CONVERTER=org.apache.kafka.connect.json.JsonConverter \
  -e CONNECT_BOOTSTRAP_SERVERS=host.docker.internal:19092 \
  -e CONNECT_PLUGIN_PATH="/${PLUGIN_PATH}" \
  -e CONNECT_LISTENERS=http://0.0.0.0:18080 \
  -e CONNECT_REST_ADVERTISED_HOST_NAME=host.docker.internal \
  -e CONNECT_REST_PORT=18080 \
  -e CONNECT_CONFIG_STORAGE_REPLICATION_FACTOR=1 \
  -e CONNECT_STATUS_STORAGE_REPLICATION_FACTOR=1 \
  -e CONNECT_OFFSET_STORAGE_REPLICATION_FACTOR=1 \
  -p 18080:18080 \
  --mount type=bind,src="${PROJECT_PLUGIN_PATH}",dst="${PLUGIN_PATH}" \
  -v kafka_connect_jars:${JARS_PATH} \
  -v kafka_connect_secrets:${SECRET_PATH} \
  -v kafka_secret1:${SECRET_KAFKA_PATH} \
  -v kafka_connect_data:${DATA_KAFKA} \
  confluentinc/cp-kafka-connect:7.7.5
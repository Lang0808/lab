# Create a cluster of kafka with 3 brokers.
# Broker1 is a controller.
# Broker2 and broker3 are brokers

KAFKA_DATA_DIR=/var/lib/kafka/data
KAFKA_SHARED_CONFIG_DIR=/mnt/shared/config
KAFKA_SECRET_DIR=/etc/kafka/secrets

# Create a volume to store kafka logs
docker volume create kafka_data1 || true
docker volume create kafka_config1 || true
docker volume create kafka_secret1 || true

docker volume create kafka_data2 || true
docker volume create kafka_config2 || true
docker volume create kafka_secret2 || true

docker volume create kafka_data3 || true
docker volume create kafka_config3 || true
docker volume create kafka_secret3 || true

# Delete all running containers
docker stop kafka1 || true
docker rm kafka1 || true
docker stop kafka2 || true
docker rm kafka2 || true
docker stop kafka3 || true
docker rm kafka3 || true

# Broker 1 is controller
# Only have 1 listener, CONTROLLER
# No need of advertised listeners because user never calls to this broker
docker run -d  \
  --name kafka1 \
  -e KAFKA_NODE_ID=1 \
  -e KAFKA_PROCESS_ROLES=controller \
  -e KAFKA_LISTENERS=CONTROLLER://0.0.0.0:9093 \
  -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT \
  -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@host.docker.internal:9093 \
  -e KAFKA_AUTO_CREATE_TOPICS_ENABLE=true \
  -v kafka_data1:${KAFKA_DATA_DIR} \
  -v kafka_config1:${KAFKA_SHARED_CONFIG_DIR} \
  -v kafka_secret1:${KAFKA_SECRET_DIR} \
  -p 9093:9093 \
  apache/kafka:4.0.0

# Broker 2 is broker
docker run -d  \
  --name kafka2 \
  -e KAFKA_NODE_ID=2 \
  -e KAFKA_PROCESS_ROLES=broker \
  -e KAFKA_LISTENERS=NORMAL://0.0.0.0:19092 \
  -e KAFKA_ADVERTISED_LISTENERS=NORMAL://host.docker.internal:19092 \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=NORMAL:PLAINTEXT,CONTROLLER:PLAINTEXT \
  -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@host.docker.internal:9093 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
  -e KAFKA_INTER_BROKER_LISTENER_NAME=NORMAL \
  -e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
  -e KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS=0 \
  -e KAFKA_NUM_PARTITIONS=1 \
  -e KAFKA_AUTO_CREATE_TOPICS_ENABLE=true \
  -v kafka_data2:/var/lib/kafka/data \
  -v kafka_config2:/mnt/shared/config \
  -v kafka_secret2:/etc/kafka/secrets \
  -p 19092:19092 \
  apache/kafka:4.0.0

# Broker 3 is broker
docker run -d  \
  --name kafka3 \
  -e KAFKA_NODE_ID=3 \
  -e KAFKA_PROCESS_ROLES=broker \
  -e KAFKA_LISTENERS=NORMAL://0.0.0.0:29092 \
  -e KAFKA_ADVERTISED_LISTENERS=NORMAL://host.docker.internal:29092 \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=NORMAL:PLAINTEXT,CONTROLLER:PLAINTEXT \
  -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@host.docker.internal:9093 \
  -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_INTER_BROKER_LISTENER_NAME=NORMAL \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
  -e KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS=0 \
  -e KAFKA_NUM_PARTITIONS=1 \
  -e KAFKA_AUTO_CREATE_TOPICS_ENABLE=true \
  -v kafka_data3:${KAFKA_DATA_DIR} \
  -v kafka_config3:${KAFKA_SHARED_CONFIG_DIR} \
  -v kafka_secret3:${KAFKA_SECRET_DIR} \
  -p 29092:29092 \
  apache/kafka:4.0.0
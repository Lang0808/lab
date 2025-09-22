# Create a cluster of kafka with 3 brokers.
# Each broker is both a controller and broker role.

# Define the place where kafka brokers store their logs
KAFKA_LOG_DIR=/var/kafka/log

# Create a volume to store kafka logs
docker volume create kafka_log1 || true

docker volume create kafka_log2 || true

docker volume create kafka_log3 || true

# Create kafka containers
docker run -d  \
  --name kafka1 \
  -e KAFKA_NODE_ID=1 \
  -e KAFKA_PROCESS_ROLES=broker,controller \
  -e KAFKA_LISTENERS=NORMAL://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093 \
  -e KAFKA_ADVERTISED_LISTENERS=NORMAL://localhost:9092 \
  -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,NORMAL:PLAINTEXT \
  -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
  -e KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS=0 \
  -e KAFKA_NUM_PARTITIONS=1 \
  -e KAFKA_LOG_DIRS= ${KAFKA_LOG_DIR} \
  -v kafka_log1:${KAFKA_LOG_DIR} \
  -p 9092:9092 \
  -p 9093:9093 \
  apache/kafka:4.0.0

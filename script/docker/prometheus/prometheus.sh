docker volume create prometheus-data || true

docker stop prometheus || true
docker rm prometheus || true

docker run -d \
  --name prometheus \
  -p 9090:9090 \
  --mount type=bind,src="$(pwd)/../../../config/prometheus/prometheus.yml",dst="/etc/prometheus/prometheus.yml" \
  -v prometheus-data:/prometheus \
  prom/prometheus:v3.6.0
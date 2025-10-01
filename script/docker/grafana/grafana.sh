docker volume create grafana-storage || true

docker stop grafana || true
docker rm grafana || true

docker run -d \
  --name=grafana \
  -p 3001:3000 \
  -v grafana-storage:/var/lib/grafana \
  grafana/grafana:12.2

# Login account
# name: admin
# password: luke_grafana
# Dashboard JVM: https://grafana.com/grafana/dashboards/4701-jvm-micrometer/
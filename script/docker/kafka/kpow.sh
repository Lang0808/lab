docker stop kpow || true
docker rm kpow || true

docker run \
  -d \
  --name kpow \
  -p 3000:3000 \
  --env-file ../../../config/kpow/config.env \
  -m 2G \
  factorhouse/kpow-ce:94.5
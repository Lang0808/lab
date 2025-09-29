docker stop kpow || true
docker rm kpow || true

LICENSE_SIGNATURE=$(cat ../../../config/kpow/license_signature)

docker run \
  -d \
  --name kpow \
  -p 3000:3000 \
  -e ENVIRONMENT_NAME="Lab Kafka Monitor" \
  -e BOOTSTRAP="host.docker.internal:19092" \
  -e LICENSE_ID=dd1633cf-b1a4-45fa-a7f0-b684ed739b24 \
  -e LICENSE_CODE=KPOW_COMMUNITY \
  -e LICENSEE="Dinh Bao" \
  -e LICENSE_EXPIRY=2026-09-24 \
  -e LICENSE_SIGNATURE="${LICENSE_SIGNATURE}" \
  -e CONNECT_NAME=LUKE \
  -e CONNECT_REST_URL=http://host.docker.internal:18080 \
  -m 2G \
  factorhouse/kpow-ce:94.5
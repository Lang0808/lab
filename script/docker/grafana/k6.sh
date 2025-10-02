docker stop k6 || true
docker rm k6 || true

docker run \
  --name k6 \
  --mount type=bind,src="$(pwd)/script.js",dst="/scripts/script.js" \
  grafana/k6:1.3.0-with-browser run "//scripts/script.js"
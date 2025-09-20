# Create database in docker for lab01

# create volume for old database
docker volume create new_db_volume || true

# Stop and delete old database container
docker stop lab01_new_db || true
docker rm lab01_new_db || true

# Create old database
docker run \
  --name lab01_new_db \
  -e MYSQL_ROOT_PASSWORD=luke_root \
  -e MYSQL_USER=luke_luke \
  -e MYSQL_PASSWORD=luke_password_new \
  -e MYSQL_DATABASE=luke \
  -d \
  -p 3308:3306 \
  -v new_db_volume:/var/lib/mysql \
  mysql:8.4.6

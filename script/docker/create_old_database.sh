# Create database in docker for lab01

# create volume for old database
docker volume create old_db_volume || true

# Stop and delete old database container
docker stop lab01_old_db || true && docker rm lab01_old_db || true

# Create old database
docker run \
  --name lab01_old_db \
  -e MYSQL_ROOT_PASSWORD=luke_root \
  -e MYSQL_USER=luke_luke \
  -e MYSQL_PASSWORD=luke_password_old \
  -e MYSQL_DATABASE=luke \
  -d \
  -p 3307:3306 \
  -v old_db_volume:/var/lib/mysql \
  mysql:8.4.6

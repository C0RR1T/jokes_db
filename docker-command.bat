docker stop jokes_db
docker rm jokes_db
docker run --rm --name jokes_db -d -p 5432:5432 -e POSTGRES_PASSWORD=localpass -e POSTGRES_DB=localdb -e POSTGRES_USER=localuser postgres
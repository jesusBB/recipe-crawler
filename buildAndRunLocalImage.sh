echo ------ Building jar file -------
./gradlew bootJar
echo ------- Building spring-boot-recipe-crawler image -------
docker build -t spring-boot-recipe-crawler:latest .
echo ------- Running spring-boot-recipe-crawler image -------
docker run -d -p 8090:8085 spring-boot-recipe-crawler:latest
exit 0
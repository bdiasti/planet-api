#Muda de diretorio para realizar build maven
cd planet-service

#Realiza build do java e gera .jar
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install

#Volta para o home da aplicação
cd ../docker

#Sobe toda a stack
docker-compose -f docker-compose.yml down
docker-compose -f docker-compose.yml up

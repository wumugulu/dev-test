# 利用maven镜像下载代码,构建应用
docker rm -f tmp-maven
docker run -it --name tmp-maven -v /home/apps:/home/apps-docker \
      maven:3.8.4-jdk-8 /bin/bash -c \
      "cd /home/apps-docker && \
      rm -rf /home/apps-docker/* && \
      git clone git://github.com/wumugulu/dev-test.git && \
      cd dev-test && \
      mvn clean package && \
      exit"

# 拉取,并运行nacos
docker pull nacos/nacos-server:2.0.3-slim
docker run -d --name test-nacos -e MODE=standalone -e JVM_XMS=128m -e JVM_XMX=128m -p 8848:8848 nacos/nacos-server:2.0.3-slim

# 构建,并运行自己的mysql
cd /home/apps/dev-test/file/shell/mysql
docker build -t wumugulu/mysql:0.2 .
docker run -d --name test-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -p 33060:33060 wumugulu/mysql:0.2

# 构建,并运行自己的app容器
# COPY $APP_SOURCE_DIR/TestGateway/target/TestGateway-1.0.jar /home/apps/app-gateway/
# COPY $APP_SOURCE_DIR/TestService/TestMail/target/TestMail-1.0.jar /home/apps/app-service-mail/
# COPY $APP_SOURCE_DIR/TestService/TestUser/target/TestUser-1.0.jar /home/apps/app-service-user/
# COPY $APP_SOURCE_DIR/TestWeb/target/TestWeb-1.0.jar /home/apps/app-web/
# COPY $APP_SOURCE_DIR/file/shell/apps/startup.sh /home/apps/
cd /home/apps/dev-test/file/shell/apps
sudo rm -rf jar
sudo mkdir jar

sudo cp -f /home/apps/dev-test/TestGateway/target/TestGateway-1.0.jar ./jar
sudo cp -f /home/apps/dev-test/TestService/TestMail/target/TestMail-1.0.jar ./jar
sudo cp -f /home/apps/dev-test/TestService/TestUser/target/TestUser-1.0.jar ./jar
sudo cp -f /home/apps/dev-test/TestWeb/target/TestWeb-1.0.jar ./jar
sudo cp -f /home/apps/dev-test/file/shell/apps/startup.sh ./jar

docker rmi -f wumugulu/myapps:0.3
docker build -t wumugulu/myapps:0.3 .

docker rm -f test-apps
docker run -d --name test-apps -p 8000:8000 --link test-nacos:nacos-host --link test-mysql:mysql-host wumugulu/myapps:0.3
# docker run -it --name test-apps -p 8000:8000 --link test-nacos:nacos-host --link test-mysql:mysql-host wumugulu/myapps:0.3 /bin/bash
docker ps

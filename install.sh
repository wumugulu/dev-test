

# 利用maven镜像下载代码,构建应用
echo
echo ----------------1. 开始下载代码,构建应用......
echo

DEMO_FILE_DIR=/home/apps
sudo rm -f $DEMO_FILE_DIR/*
docker rm -f tmp-maven
docker run -it --name tmp-maven -v $DEMO_FILE_DIR:/home/apps-docker maven:3.8.4-jdk-8
      /bin/bash -c \
      "cd /home/apps-docker && \
      rm -rf /home/apps-docker/* && \
      git clone git://github.com/wumugulu/dev-test.git && \
      cd dev-test && \
      mvn clean package && \
      exit"

# 开始部署 ...
echo
echo ----------------2. 开始部署......
echo

# 1. 拉取,并运行nacos
echo
echo ------------------2.1 开始运行nacos容器......
echo
# docker pull nacos/nacos-server:2.0.3-slim
docker rm -f test-nacos
docker run -d --name test-nacos -e MODE=standalone -e JVM_XMS=128m -e JVM_XMX=128m -p 8848:8848 nacos/nacos-server:2.0.3-slim

# 2. 部署myslq以及app
echo
echo ------------------2.2 开始构建和运行mysql容器......
echo
DEMO_PROJECT_DIR=$DEMO_FILE_DIR/dev-test

# 构建,并运行自己的mysql
DEMO_MYSQL_DIR=$DEMO_PROJECT_DIR/file/shell/mysql
cd $DEMO_MYSQL_DIR
docker build -t wumugulu/mysql:0.2 .
docker rm -f test-mysql
docker run -d --name test-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -p 33060:33060 wumugulu/mysql:0.2

# 构建,并运行自己的app容器
echo
echo ------------------2.3 开始构建和运行apps容器......
echo
# 3.准备jar包及启动脚本
DEMO_APPS_DIR=$DEMO_PROJECT_DIR/file/shell/apps
sudo rm -rf $DEMO_APPS_DIR/jar
sudo mkdir -p $DEMO_APPS_DIR/jar
cd $DEMO_APPS_DIR

sudo cp -f $DEMO_PROJECT_DIR/TestGateway/target/TestGateway-1.0.jar ./jar
sudo cp -f $DEMO_PROJECT_DIR/TestService/TestMail/target/TestMail-1.0.jar ./jar
sudo cp -f $DEMO_PROJECT_DIR/TestService/TestUser/target/TestUser-1.0.jar ./jar
sudo cp -f $DEMO_PROJECT_DIR/TestWeb/target/TestWeb-1.0.jar ./jar
sudo cp -f $DEMO_PROJECT_DIR/file/shell/apps/startup.sh ./jar

# 2.构建apps镜像
docker rmi -f wumugulu/myapps:0.3
docker build -t wumugulu/myapps:0.3 .

# 3.启动apps镜像
docker rm -f test-apps
docker run -d --name test-apps -p 8000:8000 --link test-nacos:nacos-host --link test-mysql:mysql-host wumugulu/myapps:0.3
# docker run -it --name test-apps -p 8000:8000 --link test-nacos:nacos-host --link test-mysql:mysql-host wumugulu/myapps:0.3 /bin/bash

docker ps
# docker logs -f test-apps

echo
echo ----------------3. 部署完成!!!
echo

echo please open your browser and access:  http://[YOUR_DOCKER_HOST_IP]:8000/

# 利用maven镜像下载代码,构建应用
docker run -it --name tmp-maven -v /home/apps:/home/apps-docker maven:3.8.4-jdk-8 /bin/bash -c "cd /home/apps-docker && git clone git://github.com/wumugulu/dev-test.git && cd dev-test && mvn clean package && exit"

# 拉取,构建,并运行自己的mysql

# 拉取,并运行nacos

# 构建,并运行自己的app容器



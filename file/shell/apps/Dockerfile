FROM java:8-jre

MAINTAINER wumugulu<f.ky@qq.com>

COPY TestGateway/target/TestGateway-1.0.jar /home/apps/app-gateway/

COPY TestService/TestMail/target/TestMail-1.0.jar /home/apps/app-service-mail/

COPY TestService/TestUser/target/TestUser-1.0.jar /home/apps/app-service-user/

COPY TestWeb/target/TestWeb-1.0.jar /home/apps/app-web/

COPY file/shell/apps/startup.sh /home/apps/

CMD ["chmod", "+x", "/home/apps/startup.sh"]

EXPOSE 8000

CMD ["sh", "-c", "/home/apps/startup.sh"]

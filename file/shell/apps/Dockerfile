FROM java:8-jre

MAINTAINER wumugulu<f.ky@qq.com>

COPY ./jar/TestGateway-1.0.jar /home/apps/app-gateway/

COPY ./jar/TestMail-1.0.jar /home/apps/app-service-mail/

COPY ./jar/TestUser-1.0.jar /home/apps/app-service-user/

COPY ./jar/TestWeb-1.0.jar /home/apps/app-web/

COPY ./jar/startup.sh /home/apps/

RUN chmod +x /home/apps/startup.sh
#CMD ["chmod", "+x", "/home/apps/startup.sh"]

EXPOSE 8000

CMD bash /home/apps/startup.sh

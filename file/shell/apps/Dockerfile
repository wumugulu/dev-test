FROM java:8-jre

MAINTAINER wumugulu<f.ky@qq.com>

ADD ./jar/TestGateway-1.0.jar /home/apps/app-gateway/

ADD ./jar/TestMail-1.0.jar /home/apps/app-service-mail/

ADD ./jar/TestUser-1.0.jar /home/apps/app-service-user/

ADD ./jar/TestWeb-1.0.jar /home/apps/app-web/

ADD ./jar/startup.sh /home/apps/

RUN chmod +x /home/apps/startup.sh
#CMD ["chmod", "+x", "/home/apps/startup.sh"]

EXPOSE 8000 9000

#ENTRYPOINT ["java", "-jar", "-Xmx128m", "-XX:MaxMetaspaceSize=128m", "-XX:CompressedClassSpaceSize=24m", "/home/apps/app-web/TestWeb-1.0.jar"]
#ENTRYPOINT ["/home/apps/startup.sh"]
# this is ok: CMD sh -c "/home/apps/startup.sh && java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m /home/apps/app-web/TestWeb-1.0.jar"

#CMD sh -c "/home/apps/start.sh"a
ENTRYPOINT ["sh", "-c", "/home/apps/startup.sh"]

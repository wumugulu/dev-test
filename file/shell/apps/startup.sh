#!/bin/bash

# nohup java -jar /home/apps/app-gateway/TestGateway-1.0.jar > /dev/null 2>&1 &
# nohup java -jar /home/apps/app-service-mail/TestMail-1.0.jar > /dev/null 2>&1 &
# ohup java -jar /home/apps/app-service-user/TestUser-1.0.jar > /dev/null 2>&1 &
# nohup java -jar /home/apps/app-web/TestWeb-1.0.jar > /dev/null 2>&1 &

#echo "0: `date`" >> /tmp/apps-docker/debug.log

sleep 3s
nohup java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m /home/apps/app-gateway/TestGateway-1.0.jar &

sleep 3s
nohup java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m /home/apps/app-service-mail/TestMail-1.0.jar &

sleep 3s
nohup java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m /home/apps/app-service-user/TestUser-1.0.jar &

sleep 3s
nohup java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m /home/apps/app-web/TestWeb-1.0.jar &

while [[ true ]]; do
    sleep 3
done

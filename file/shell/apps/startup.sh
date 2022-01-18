
# nohup java -jar /home/apps/app-gateway/TestGateway-1.0.jar > /dev/null 2>&1 &
# nohup java -jar /home/apps/app-service-mail/TestMail-1.0.jar > /dev/null 2>&1 &
# ohup java -jar /home/apps/app-service-user/TestUser-1.0.jar > /dev/null 2>&1 &
# nohup java -jar /home/apps/app-web/TestWeb-1.0.jar > /dev/null 2>&1 &

java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m  /home/apps/app-gateway/TestGateway-1.0.jar > /dev/null &
sleep 3s
java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m  /home/apps/app-service-mail/TestMail-1.0.jar > /dev/null &
sleep 3s
java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m  /home/apps/app-service-user/TestUser-1.0.jar > /dev/null &
sleep 3s
java -jar -Xmx128m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=24m  /home/apps/app-web/TestWeb-1.0.jar > /dev/null &
sleep 3s

while [[ true ]]; do
  sleep 5s
done

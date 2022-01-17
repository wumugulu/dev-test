# begin to download java jre/jdk
# wget -P /tmp/ http://66-ai.com/download/script-litte-prince/jdk-8u261-linux-x64.tar.gz
wget -P /tmp/ https://hny.guiren21.com/201704/tools/jdk-linux-i586.tar.gz

tar zxvf /opt/java/jdk-8u181-linux-x64.tar.gz -C /opt/java > /dev/null 2>&1
echo '# JAVA-8u181' >> /etc/profile
echo 'JAVA_HOME=/opt/java/jdk1.8.0_181' >> /etc/profile
echo 'JAVA_BIN=/opt/java/jdk1.8.0_181/bin' >> /etc/profile
echo 'PATH=$PATH:$JAVA_BIN' >> /etc/profile
echo 'CLASSPATH=$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar' >> /etc/profile
echo 'export JAVA_HOME JAVA_BIN PATH CLASSPATH' >> /etc/profile
source /etc/profile
echo "java is already installed,please open another session to test it "

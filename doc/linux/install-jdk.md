下载
wget -c http://download.oracle.com/otn-pub/java/jdk/7u17-b02/jdk-7u17-linux-x64.tar.gz
(jre: http://download.oracle.com/otn-pub/java/jdk/7u51-b13/server-jre-7u51-linux-x64.tar.gz )
解压
sudo tar zxvf ./jdk-7-linux-i586.tar.gz  -C /usr/lib/jvm
cd /usr/lib/jvm
sudo li -s jdk1.7.0/ java-7-sun
配置环境变量
vi /etc/bash.bashrc
java环境变量
export JAVA_HOME=/usr/lib/jvm/java-7-sun
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH
使环境变量生效
source /etc/bash.bashrc
配置JDK默认版本
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-7-sun/bin/java 300
sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/java-7-sun/bin/javac 300
sudo update-alternatives --install /usr/bin/jar jar /usr/lib/jvm/java-7-sun/bin/jar 300 
sudo update-alternatives --install /usr/bin/javah javah /usr/lib/jvm/java-7-sun/bin/javah 300 
sudo update-alternatives --install /usr/bin/javap javap /usr/lib/jvm/java-7-sun/bin/javap 300 
sudo update-alternatives --config java
download
```````
wget http://mirrors.hust.edu.cn/apache/zookeeper/stable/zookeeper-3.4.5.tar.gz
#extract
tar zxvf zookeeper-3.4.5.tar.gz -C /opt/server
#copy and edit the config file
cd /opt/server/zookeeper-3.4.5/conf
cp zoo_sample.cfg zoo.cfg
vi zoo.cfg
``````

append cluster info
``````
server.{myid}={host}:2888:3888
``````

add myid file to zookeeper dataDir
``````
mkdir {dataDir}
cd {dataDir}
echo "{myid}">myid
``````

#sample:
cluster info in cfg:
``````
server.1=192.168.128.135:2888:3888
``````

content in /tmp/zookeeper/myid
``````
1
``````

#start zookeeper cluster in zookeeper_home directory(run the command in each cluster):
``````
java -cp zookeeper-3.4.5.jar:lib/*:conf  org.apache.zookeeper.server.quorum.QuorumPeerMain conf/zoo.cfg
``````
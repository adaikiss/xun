#install native dependencies(refer to installing-storms-native-dependencies-on-ubuntu-1204[http://www.christophermaness.com/installing-storms-native-dependencies-on-ubuntu-1204]):
download and install ZeroMQ(require gcc/g++/make):
``````
wget http://download.zeromq.org/zeromq-4.0.3.tar.gz
tar zxvf zeromq-4.0.3.tar.gz -C /opt/server
cd /opt/server/zeromq-4.0.3
./configure
make
sudu make install
``````

download and install JZMQ(require git for download):
``````
git clone https://github.com/nathanmarz/jzmq.git
cd jzmq
cd src
touch classdist_noinst.stamp
javac -d . org/zeromq/ZMQ.java org/zeromq/ZMQException.java org/zeromq/ZMQQueue.java org/zeromq/ZMQForwarder.java org/zeromq/ZMQStreamer.java
cd ..
./autogen.sh
./configure
make
sudo make install
``````

#install storm
download:
``````
wget https://dl.dropboxusercontent.com/s/tqdpoif32gufapo/storm-0.9.0.1.tar.gz
``````

extract:
``````
tar zxvf storm-0.9.1.tar.gz /opt/server/
``````

duplicate and modify conf file:
``````
cd /opt/server/storm-0.9.1/conf
cp storm.yaml storm_original.yaml
vi storm.yaml
``````
content in storm.yaml(nimbus):
``````
#list all zookeeper servers.
storm.zookeeper.servers:
     - "192.168.128.135"
     - "192.168.128.136"
	 - "192.168.128.137"

#blank line required after map-list.

nimbus.host: "localhost"
storm.local.dir: "/var/stormtmp"
java.library.path: "/usr/local/lib"

#extra configs
nimbus.task.launch.secs: 240
supervisor.worker.start.timeout.secs: 240
supervisor.worker.timeout.secs: 240

worker.childopts: "-Xmx768m"
nimbus.childopts: "-Xmx512m"
supervisor.childopts: "-Xmx256m"
``````

content in storm.yaml(supervisor):
``````
#list all zookeeper servers.
storm.zookeeper.servers:
     - "192.168.128.135"
     - "192.168.128.136"
	 - "192.168.128.137"

#nimbus host
nimbus.host: "192.168.128.138"
storm.local.dir: "/var/stormtmp"
java.library.path: "/usr/local/lib"

supervisor.slots.ports:
     - 6700
     - 6701
     - 6702
     - 6703

#extra configs
nimbus.task.launch.secs: 240
supervisor.worker.start.timeout.secs: 240
supervisor.worker.timeout.secs: 240

worker.childopts: "-Xmx768m"
nimbus.childopts: "-Xmx512m"
supervisor.childopts: "-Xmx256m"
``````
copy storm.yaml to home path(for each node):
``````
mkdir ~/.storm
cp storm.yaml ~/.storm
``````

#start cluster
start nimbus(in nimbus node)
```````
{storm_home}/bin/storm nimbus
```````
start ui, and see status in http://localhost:8080(in nimbus node)
``````
{storm_home}/bin/storm ui
``````

start supervisor(in each supervisor nodes)
``````
{storm_home}/bin/storm supervisor
``````

submit a topology:
``````
{storm_home}/bin/storm jar {path_of_topology_jar} {full_class_name_of_topology} {args_will_be_passed_to_topology_class}
``````

kill a topology:
``````
{storm_home}/bin/storm kill {topology_name}
``````
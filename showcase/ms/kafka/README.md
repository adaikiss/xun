[Apache Kafka](http://kafka.apache.org/) Showcase
=========
Apache Kafka is publish-subscribe messaging rethought as a distributed commit log. It's designed from [Building LinkedIn’s Real-time Activity Data Pipeline](http://sites.computer.org/debull/A12june/pipeline.pdf)

Kafka gettingStarted from the [Kafka gettingStarted](http://kafka.apache.org/documentation.html#gettingStarted)

#preparation
install [sun-jdk](http://docs.oracle.com/javase/7/docs/webnotes/install/linux/linux-jdk.html) & [SBT](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html)

#Installation
* [Download](http://kafka.apache.org/downloads.html) kafka.
```
wget https://dist.apache.org/repos/dist/release/kafka/kafka_2.8.0-0.8.0-beta1.tgz
```

* Install
```
tar zxf kafka_2.8.0-0.8.0-beta1.tgz -C /opt/server
cd /opt/server/kafka_2.8.0-0.8.0-beta1
./sbt update
./sbt package
./sbt sbt-dependency
```

* Start the server
start internal zookeeper and kafka server
```
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

* Create a topic
create a topic named test
```
bin/kafka-create-topic.sh --zookeeper localhost:2181 --replica 1 --partition 1 --topic test
```
list the topics
```
bin/kafka-list-topic.sh --zookeeper localhost:2181
```

* Send some messages
run the following command in a new terminal
```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
```
enter some message in terminal.

* Start a consumer
run the following command in a new terminal
```
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
```
you will see all messages from the producer.

more detail tutorial [here](http://kafka.apache.org/documentation.html#gettingStarted)
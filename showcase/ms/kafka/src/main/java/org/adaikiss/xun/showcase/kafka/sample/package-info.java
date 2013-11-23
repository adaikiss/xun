/**
 * steps:
 * 1. start zookeeper and kafka server.
 * 2. create a topic named 'dota' with the command:
 *  ${kafka_home}/bin/kafka-create-topic.sh --zookeeper localhost:2181 --replica 3 partition 1 --topic dota
 * 3. run HighLevelConsumerExample & ConsumerGroupExample.
 * 4. enter messages in HighLevelConsumerExample console and get message in ConsumerGroupExample.
 */
/**
 * @author HuLingwei
 *
 */
package org.adaikiss.xun.showcase.kafka.sample;

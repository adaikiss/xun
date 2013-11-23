package org.adaikiss.xun.showcase.kafka.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
 
public class ConsumerGroupExample {
    private final ConsumerConnector consumer;
    private final String topic;
    private  ExecutorService executor;
 
    public ConsumerGroupExample(String zookeeper, String groupId, String topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig(zookeeper, groupId));
        this.topic = topic;
    }
 
    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (executor != null) executor.shutdown();
    }
 
    public void run(int threads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(threads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        executor = Executors.newFixedThreadPool(threads);
        int name = 'a';
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            executor.submit(new ConsumerTest(stream, String.valueOf((char)(name++))));
        }
        executor.shutdown();
    }
 
    private static ConsumerConfig createConsumerConfig(String zookeeper, String groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeper);
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "6000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
 
        return new ConsumerConfig(props);
    }
 
    public static void main(String[] args) {
        String zooKeeper = "192.168.128.130:2181";
        String groupId = "group1";
        String topic = "dota";
        int threads = 4;
 
        ConsumerGroupExample example = new ConsumerGroupExample(zooKeeper, groupId, topic);
        example.run(threads);
    }

    class ConsumerTest implements Runnable {
    	private KafkaStream<byte[], byte[]> stream;
    	private String name;

    	public ConsumerTest(KafkaStream<byte[], byte[]> stream, String name) {
    		this.name = name;
    		this.stream = stream;
    	}

    	public void run() {
    		ConsumerIterator<byte[], byte[]> it = stream.iterator();
    		while (it.hasNext())
    			System.err.println("Thread " + name + ": "
    					+ new String(it.next().message()));
    		System.out.println("Shutting down Thread: " + name);
    	}
    }
}
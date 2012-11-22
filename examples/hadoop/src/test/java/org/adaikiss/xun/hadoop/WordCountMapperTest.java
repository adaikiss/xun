/**
 * 
 */
package org.adaikiss.xun.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Administrator
 * 
 */
public class WordCountMapperTest {
	private Mapper<Object, Text, Text, IntWritable> mapper;
	private MapDriver<Object, Text, Text, IntWritable> driver;

	@Before
	public void init() {
		mapper = new WordCountMapper();
		driver = new MapDriver<Object, Text, Text, IntWritable>(mapper);
	}

	@Test
	public void testReduce() throws IOException {
		String line = "Taobao is a weak website";
		driver.withInput(null, new Text(line))
				.withOutput(new Text("Taobao"), new IntWritable(1))
				.withOutput(new Text("is"), new IntWritable(1))
				.withOutput(new Text("a"), new IntWritable(1))
				.withOutput(new Text("weak"), new IntWritable(1))
				.withOutput(new Text("website"), new IntWritable(1)).runTest();
	}
}

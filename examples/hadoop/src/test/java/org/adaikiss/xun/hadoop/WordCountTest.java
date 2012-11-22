/**
 * 
 */
package org.adaikiss.xun.hadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.testutil.ExtendedAssert;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Administrator
 * 
 */
public class WordCountTest {
	private Mapper<Object, Text, Text, IntWritable> mapper;
	private Reducer<Text, IntWritable, Text, IntWritable> reducer;
	private MapReduceDriver<Object, Text, Text, IntWritable, Text, IntWritable> driver;

	@Before
	public void init() {
		mapper = new WordCountMapper();
		reducer = new WordCountReducer();
		driver = new MapReduceDriver<Object, Text, Text, IntWritable, Text, IntWritable>(mapper, reducer);
	}

	@Test
	public void test() throws RuntimeException, IOException {
		String line = "Taobao is a weak website is it not";
		List<Pair<Text, IntWritable>> out = null;
		out = driver.withInput("", new Text(line)).run();
		List<Pair<Text, IntWritable>> expected = new ArrayList<Pair<Text, IntWritable>>();
		expected.add(new Pair<Text, IntWritable>(new Text("Taobao"), new IntWritable(1)));
		expected.add(new Pair<Text, IntWritable>(new Text("a"), new IntWritable(1)));
		expected.add(new Pair<Text, IntWritable>(new Text("is"), new IntWritable(2)));
		expected.add(new Pair<Text, IntWritable>(new Text("it"), new IntWritable(1)));
		expected.add(new Pair<Text, IntWritable>(new Text("not"), new IntWritable(1)));
		expected.add(new Pair<Text, IntWritable>(new Text("weak"), new IntWritable(1)));
		expected.add(new Pair<Text, IntWritable>(new Text("website"), new IntWritable(1)));
		ExtendedAssert.assertListEquals(expected, out);
	}
}

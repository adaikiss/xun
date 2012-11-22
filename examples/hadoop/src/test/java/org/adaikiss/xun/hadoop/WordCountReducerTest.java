/**
 * 
 */
package org.adaikiss.xun.hadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class WordCountReducerTest {

	private Reducer<Text, IntWritable, Text, IntWritable> reducer;
	  private ReduceDriver<Text, IntWritable, Text, IntWritable> driver;
	  @Before
	   public void init(){
	     reducer = new WordCountReducer();
	     driver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(reducer);
	   }
	  @Test
	  public void testMap() throws IOException{
	    String key = "taobao";
	    List<IntWritable> values = new ArrayList<IntWritable>();
	    values.add(new IntWritable(2));
	    values.add(new IntWritable(3));
	    driver.withInput(new Text(key), values)
	           .withOutput(new Text(key), new IntWritable(5))
	           .runTest();
	   }
}

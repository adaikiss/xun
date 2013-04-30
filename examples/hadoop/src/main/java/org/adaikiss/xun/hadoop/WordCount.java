/**
 * 
 */
package org.adaikiss.xun.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * @author Administrator
 * 
 */
public class WordCount {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: wordcount  ");
			System.exit(2);
		}
		/** 创建一个job，起个名字以便跟踪查看任务执行情况 **/
		Job job = new Job(conf, "word count");
		/**
		 * 当在hadoop集群上运行作业时，需要把代码打包成一个jar文件（hadoop会在集群分发这个文件），
		 * 通过job的setJarByClass设置一个类，hadoop根据这个类找到所在的jar文件
		 **/
		job.setJarByClass(WordCount.class);
		/** 设置要使用的map、combiner、reduce类型 **/
		job.setMapperClass(WordCountMapper.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		/**
		 * 设置map和reduce函数的输入类型，这里没有代码是因为我们使用默认的TextInputFormat，针对文本文件，按行将文本文件切割成
		 * InputSplits, 并用 LineRecordReader 将 InputSplit 解析成 <key,value&gt:
		 * 对，key 是行在文件中的位置，value 是文件中的一行
		 **/
		/** 设置map和reduce函数的输出键和输出值类型 **/
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		/** 设置输入和输出路径 **/

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));

		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		/** 提交作业并等待它完成 **/
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

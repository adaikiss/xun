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
		/** ����һ��job����������Ա���ٲ鿴����ִ����� **/
		Job job = new Job(conf, "word count");
		/**
		 * ����hadoop��Ⱥ��������ҵʱ����Ҫ�Ѵ�������һ��jar�ļ���hadoop���ڼ�Ⱥ�ַ�����ļ�����
		 * ͨ��job��setJarByClass����һ���࣬hadoop����������ҵ����ڵ�jar�ļ�
		 **/
		job.setJarByClass(WordCount.class);
		/** ����Ҫʹ�õ�map��combiner��reduce���� **/
		job.setMapperClass(WordCountMapper.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		/**
		 * ����map��reduce�������������ͣ�����û�д�������Ϊ����ʹ��Ĭ�ϵ�TextInputFormat������ı��ļ������н��ı��ļ��и��
		 * InputSplits, ���� LineRecordReader �� InputSplit ������ <key,value&gt:
		 * �ԣ�key �������ļ��е�λ�ã�value ���ļ��е�һ��
		 **/
		/** ����map��reduce����������������ֵ���� **/
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		/** ������������·�� **/

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));

		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		/** �ύ��ҵ���ȴ������ **/
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

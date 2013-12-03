/**
 * 
 */
package org.adaikiss.xun.file;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;

/**
 * @author HuLingwei
 *
 */
public class MappedByteBufferShowcase {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String path = MappedByteBufferShowcase.class.getResource("/data/Illusion-c1.txt").getFile();
		RandomAccessFile f = new RandomAccessFile(path, "r");
		FileChannel channel = f.getChannel();
		MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
		buffer.load();
		byte[] bs = new byte[buffer.limit()];
		buffer.get(bs);
		buffer.clear();
		channel.close();
		f.close();
		String s = new String(bs, Charset.forName("UTF-8"));
		System.out.println(s);
	}

}

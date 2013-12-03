/**
 * 
 */
package org.adaikiss.xun.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author HuLingwei
 *
 */
public class FileChannelShowcase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomAccessFile f = null;
		FileChannel channel = null;
		ByteBuffer buffer = null;
		try {
			String path = FileChannelShowcase.class.getResource("/data/Illusion-c1.txt").getFile();
			f = new RandomAccessFile(path, "r");
			channel = f.getChannel();
			buffer = ByteBuffer.allocate((int)channel.size());
			channel.read(buffer);
			buffer.rewind();
			byte[] bs = new byte[(int)channel.size()];
			for(int i = 0;i<buffer.limit();i++){
				bs[i] = buffer.get();
			}
			System.out.println(new String(bs, Charset.forName("UTF-8")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			buffer.clear();
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

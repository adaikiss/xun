/**
 * 
 */
package org.adaikiss.xun.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author hlw
 * 
 */
public class EncodeUtil {
	public static ByteBuffer StringToByteBuffer(String message) {
		try {
			Charset charset = Charset.forName("UTF-8");
			CharsetEncoder encoder = charset.newEncoder();
			encoder.reset();
			ByteBuffer buffer = encoder.encode(CharBuffer.wrap(message));
			return buffer;
		} catch (CharacterCodingException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	public static String ByteBufferToString(ByteBuffer buf) {
		try {
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();

			CharBuffer charBuffer = decoder.decode(buf);
			// remove '\n' from the end of the line
			if (charBuffer.get(charBuffer.length() - 1) == '\n') {
				charBuffer = charBuffer.subSequence(0, charBuffer.length() - 1);
			}
			return charBuffer.toString();
		} catch (CharacterCodingException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
}

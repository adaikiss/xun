/**
 * 上午10:30:46
 */
package org.adaikiss.kay.trys;

/**
 * hlw
 * 
 */
public class StringBufferBuilderTry {
	private StringBuilder stringBuilder;
	private StringBuffer stringBuffer;
	private int times = 20;
	public StringBufferBuilderTry(StringBuffer bd, StringBuilder bf) {
		this.stringBuffer = bd;
		this.stringBuilder = bf;
	}

	public void runTest() {
		BuilderAppender da = new BuilderAppender("-a");
		BuilderAppender db = new BuilderAppender("-b");
		BufferAppender fa = new BufferAppender("-a");
		BufferAppender fb = new BufferAppender("-b");
		da.start();
		db.start();
		fa.start();
		fb.start();
		while (true) {
			if (!da.isAlive() && !db.isAlive() && !fa.isAlive()
					&& !fb.isAlive()) {
				System.out.println(this.stringBuffer);
				System.out.println(this.stringBuilder);
				System.out.println(this.stringBuffer.toString().equals(this.stringBuilder.toString()));
				break;
			}
		}
	}

	class BuilderAppender extends Thread {
		private String name;

		public BuilderAppender(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < times; i++) {
					for (long k = 0; k < 100; k++) ; 
					stringBuilder.append(name + i);
					System.out.println(name + stringBuilder.length());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class BufferAppender extends Thread {
		private String name;

		public BufferAppender(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < times; i++) {
					for (long k = 0; k < 100; k++) ; 
					stringBuffer.append(name + i);
					System.out.println(name + stringBuffer.length());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBufferBuilderTry t = new StringBufferBuilderTry(new StringBuffer(), new StringBuilder());
		t.runTest();
	}

}

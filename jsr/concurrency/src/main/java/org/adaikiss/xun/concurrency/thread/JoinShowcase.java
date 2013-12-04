/**
 * 
 */
package org.adaikiss.xun.concurrency.thread;

/**
 * join:wait until a thread finished.
 * @author HuLingwei
 *
 */
public class JoinShowcase {

	private void initialize(){
		System.out.println("start initializing resources...");
		Thread fsResourceLoader = new Thread(new FileSystemResourceLoader());
		Thread networkResourceLoader = new Thread(new NetworkResourceLoader());
		fsResourceLoader.start();
		networkResourceLoader.start();
		try {
			fsResourceLoader.join(2000);
			//timeout
			networkResourceLoader.join(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("resources loading finished!");
		System.out.println("finished initializing resources!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JoinShowcase j = new JoinShowcase();
		j.initialize();

	}

	class FileSystemResourceLoader implements Runnable{

		@Override
		public void run() {
			try {
				System.out.println("loading resources from file system...");
				Thread.sleep(2000);
				System.out.println("finish loading resouces from file system.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	class NetworkResourceLoader implements Runnable{
		
		@Override
		public void run() {
			try {
				System.out.println("loading resources from network...");
				Thread.sleep(3000);
				System.out.println("finish loading resouces from network.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}

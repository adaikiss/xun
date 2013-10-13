/**
 * 
 */
package org.adaikiss.kay.trys.concurrency;

/**
 * @author hlw
 *
 */
public class StateSynchronizedString {
	public static String state = "false";
	private static Object lock = new Object();

	public void startSend(String name){
		synchronized(state){
			//state = "true";
			System.out.println(name + " : " + state + "-----------------");
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name + " : " + state);
		}
	}

	public static void stop(){
		//StateSynchronizedString.state = "false";
		System.out.println("set state to false!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		for(int i = 0; i < 1000; i++){			
			new Thread(i + ""){
				@Override
				public void run(){
					new StateSynchronizedString().startSend(this.getName());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		//Thread.sleep(5500);
		stop();
	}

}

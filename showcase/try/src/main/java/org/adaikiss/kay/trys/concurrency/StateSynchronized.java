/**
 * 
 */
package org.adaikiss.kay.trys.concurrency;

/**
 * @author hlw
 *
 */
public class StateSynchronized {
	public static Boolean state = false;

	public void startSend(String name){
		synchronized(state){
			state = new Boolean(true);
			System.out.println(name + " : " + state + "-----------------");
			for(int i = 0; i < 100000; i++);
			System.out.println(name + " : " + state);
		}
	}

	public static void stop(){
		StateSynchronized.state = false;
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
					new StateSynchronized().startSend(this.getName());
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

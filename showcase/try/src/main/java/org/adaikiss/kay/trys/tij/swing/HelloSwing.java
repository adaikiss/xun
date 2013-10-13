/**
 * 下午04:04:46
 */
package org.adaikiss.kay.trys.tij.swing;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * hlw
 *
 */
public class HelloSwing {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		JFrame frame = new JFrame("Hello Swing!");
		final JLabel label = new JLabel("Do you play dota?");
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);
		TimeUnit.SECONDS.sleep(1);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				int i = 10;
				try {
					//while(i-->0){
						label.setText("er?" + i);
						label.repaint();
						//TimeUnit.SECONDS.sleep(1);
					//}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

}

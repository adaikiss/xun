/**
 * 2010-12-27
 */
package org.adaikiss.kay.trys.tij.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * hlw
 *
 */
@SuppressWarnings("serial")
public class Button1 extends JFrame {
	private JButton b1 = new JButton("Button1"), b2 = new JButton("Button2");
	private final JTextField text = new JTextField(10);
	public Button1(){
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
		add(text);
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText("Button1 HAHA!");
			}
		});
		b2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				text.setText("Button2 HOHO!");
			}
		});
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingConsole.run(new Button1(), 200, 100);
	}

}

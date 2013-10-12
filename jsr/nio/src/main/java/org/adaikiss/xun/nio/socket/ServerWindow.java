/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import layout.SpringUtilities;

/**
 * @author hlw
 * 
 */
@SuppressWarnings("serial")
public class ServerWindow extends JFrame {

	private Server server;

	private boolean started;

	private JTextArea msgArea;

	public ServerWindow() {
		initUI();
		server = new Server(this);
	}

	private void initUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(300, 300);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new SpringLayout());
		JLabel hostLabel = new JLabel("Host:", JLabel.TRAILING);
		JLabel portLabel = new JLabel("Port:", JLabel.TRAILING);
		final JTextField hostField = new JTextField("127.0.0.1", 10);
		final JTextField portField = new JTextField("12345", 5);
		hostLabel.setLabelFor(hostField);
		portLabel.setLabelFor(portField);
		panel.add(hostLabel);
		panel.add(hostField);
		panel.add(portLabel);
		panel.add(portField);
		final JButton toggleBtn = new JButton("Start");
		toggleBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(started){
					toggleBtn.setText("Start");
					server.stop();
					started = false;
				}else{
					toggleBtn.setText("Stop");
					server.start(hostField.getText(), Integer.parseInt(portField.getText()));
					started = true;
				}
			}

		});
		panel.add(new JLabel());
		panel.add(toggleBtn);
		panel.add(new JLabel());
		msgArea = new JTextArea(20, 40);
		msgArea.setEditable(false);
		msgArea.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel.add(msgArea);
		SpringUtilities.makeCompactGrid(panel,
                4, 2, //rows, cols
                7, 7,        //initX, initY
                7, 7);
		setContentPane(panel);
		pack();
		setVisible(true);
	}

	public void write(String msg){
		msgArea.append(msg);
		msgArea.append("\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ServerWindow();
	}

}

/**
 * 
 */
package org.adaikiss.xun.chat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author hlw
 * 
 */
public class ChatClientWindow {
	private ChatClient client;
	private String host;
	private int port;
	private JFrame frame;
	private JPanel p;
	private JPanel p1;
	private JPanel p2;
	private JLabel hostLabel;
	private JTextField hostField;
	private JLabel portLabel;
	private JTextField portField;
	private JButton startBtn;
	private JLabel sendLabel;
	private JTextField sendField;
	private JButton sendBtn;
	private JTextArea textArea;
	private JLabel nameLabel;
	private JTextField nameField;

	public ChatClientWindow(String host, int port) {
		this.host = host;
		this.port = port;
		initUI();
		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if("start".equals(startBtn.getText())){
					client = new ChatClient(ChatClientWindow.this.host,
							ChatClientWindow.this.port, ChatClientWindow.this);
					startBtn.setText("stop");
					sendBtn.setEnabled(true);
				}else{
					client.stop();
					startBtn.setText("start");
					sendBtn.setEnabled(false);
				}
			}

		});
		sendBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				client.write(sendField.getText());
			}
			
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(p);
		frame.setVisible(true);
		frame.pack();
	}

	String getName(){
		return nameField.getText();
	}

	private void initUI() {
		frame = new JFrame();
		p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		p1 = new JPanel();
		p2 = new JPanel();
		hostLabel = new JLabel("host:");
		hostField = new JTextField(host, 10);
		hostLabel.setLabelFor(hostField);
		portLabel = new JLabel("port:");
		portField = new JTextField(port + "", 5);
		portLabel.setLabelFor(portField);
		startBtn = new JButton("start");
		p1.add(hostLabel);
		p1.add(hostField);
		p1.add(portLabel);
		p1.add(portField);
		p1.add(startBtn);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(p1, gbc);
		nameLabel = new JLabel("name:");
		nameField = new JTextField(6);
		nameLabel.setLabelFor(nameField);
		sendLabel = new JLabel("msg:");
		sendField = new JTextField(16);
		sendLabel.setLabelFor(sendField);
		sendBtn = new JButton("send");
		sendBtn.setEnabled(false);
		p2.add(nameLabel);
		p2.add(nameField);
		p2.add(sendLabel);
		p2.add(sendField);
		p2.add(sendBtn);
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(p2, gbc);
		textArea = new JTextArea(10, 20);
		gbc.gridx = 0;
		gbc.gridy = 2;
		p.add(textArea, gbc);
	}

	public void showMsg(String msg){
		this.textArea.append(msg + "\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatClientWindow("localhost", 8080);
	}

}

/**
 * 
 */
package org.adaikiss.xun.chat.oio;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
public class ChatServerWindow {
	private ChatServer server;
	private JFrame frame;
	private JPanel p;
	private JPanel p1;
	private JPanel p2;
	private JLabel portLabel;
	private JTextField portField;
	private JButton startBtn;
	private JLabel sendLabel;
	private JTextField sendField;
	private JButton sendBtn;
	JTextArea contentArea;
	
	public ChatServerWindow(){
		frame = new JFrame();
		p = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		p.setLayout(new GridBagLayout());
		p1 = new JPanel();
		portLabel = new JLabel("port:");
		portField = new JTextField("8080", 10);
		portLabel.setLabelFor(portField);
		p1.add(portLabel);
		p1.add(portField);
		startBtn = new JButton("start");
		p1.add(startBtn);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(p1, gbc);
		startBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				server = new ChatServer(Integer.parseInt(portField.getText()), ChatServerWindow.this);
				startBtn.setEnabled(false);
				sendBtn.setEnabled(true);
			}
		});
		p2 = new JPanel();
		sendLabel = new JLabel("send:");
		sendField = new JTextField(12);
		sendLabel.setLabelFor(sendField);
		sendBtn = new JButton("send");
		sendBtn.setEnabled(false);
		sendBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				server.write(sendField.getText());
			}
		});
		p2.add(sendLabel);
		p2.add(sendField);
		p2.add(sendBtn);
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(p2, gbc);
		contentArea = new JTextArea(20, 20);
		gbc.gridx = 0;
		gbc.gridy = 3;
		p.add(contentArea, gbc);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				if(server != null){
					server.stop();
				}
				super.windowClosing(e);
			}
			
		});
		frame.setContentPane(p);
		frame.setVisible(true);
		frame.pack();
	}

	public void showMsg(String msg){
		contentArea.append(msg + "\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatServerWindow();
	}

}

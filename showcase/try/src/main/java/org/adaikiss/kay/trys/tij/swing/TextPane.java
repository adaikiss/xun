/**
 * 2010-12-27
 */
package org.adaikiss.kay.trys.tij.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * hlw
 * 
 */
@SuppressWarnings("serial")
public class TextPane extends JFrame {
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem clearItem;
	private JMenuItem closeItem;
	private JMenuItem menu;
	private JMenuBar menuBar;
	private final JTextArea txt;
	private final TextPane instance;
	private File file;

	public TextPane(String title, int width, int height) {
		instance = this;
		menu = new JMenu("<html><b><font size=+1>F</font></b><html>ile");
		openItem = new JMenuItem("打开");
		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instance.openFile();
			}
		});
		menu.add(openItem);
		saveItem = new JMenuItem("保存");
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instance.save();
			}
		});
		menu.add(saveItem);
		clearItem = new JMenuItem("清空");
		clearItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt.setText("");
			}
		});
		menu.add(clearItem);
		closeItem = new JMenuItem("退出");
		closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instance.dispose();
			}
		});
		menu.add(closeItem);
		menuBar = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);
		txt = new JTextArea();
		txt.setAutoscrolls(true);
		add(txt);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
	}

	private void save() {
		String text = txt.getText();
		if(file == null){
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"TEXT Files", "txt", "*");
			chooser.setFileFilter(filter);
			int rVal = chooser.showSaveDialog(instance);
			if(rVal != JFileChooser.APPROVE_OPTION){
				return;
			}
			file = chooser.getSelectedFile();
		}
		try {
			FileWriter out = new FileWriter(file);
			out.write(text);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("Error on saving file!");
			e.printStackTrace();
		}
	}

	private void openFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"TEXT Files", "txt", "*");
		chooser.setFileFilter(filter);
		if(file!=null){
			chooser.setCurrentDirectory(file.getParentFile());
		}
		int returnVal = chooser.showOpenDialog(instance);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return;
		}
		file = chooser.getSelectedFile();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String s;
			while((s = in.readLine())!=null){
				sb.append(s);
				sb.append("\n");
			}
			txt.setText(sb.toString());
		} catch (Exception e) {
			System.out.println("Error on openning file!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TextPane("mini editor", 300, 200);
	}

}

package com.instasafe.nmapscanner;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class startHere {

	private static JFrame frame;
	private ImageIcon image;
	private static JLabel label1;
	static JProgressBar progressBar = new JProgressBar(0);;
	static JButton btnStartDownloads;
	private static JRadioButton rdbtnNewRadioButton;
	private static JRadioButton rdbtnNewRadioButton_1;
	private static JTextField txtEnterHostTo;
	static JButton btnNewButton;

	static String property = "java.io.tmpdir";
	static String tempDir = System.getProperty(property);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startHere window = new startHere();
					window.frame.setVisible(true);
					// fill();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public startHere() throws Exception {
		initialize();
		start();

	}

	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBackground(UIManager.getColor("Button.light"));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(tempDir + "scan/instasafe-icon.png"));
		frame.setBounds(100, 100, 591, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		image = new ImageIcon(getClass().getResource("/resources/instasafe-icon.png"));
		label1 = new JLabel(image);
		label1.setBounds(0, 0, 122, 39);
		progressBar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));

		progressBar.setBounds(89, 88, 370, 21);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		rdbtnNewRadioButton = new JRadioButton("All");
		rdbtnNewRadioButton.setBounds(247, 173, 40, 24);

		rdbtnNewRadioButton_1 = new JRadioButton("Top 100");
		rdbtnNewRadioButton_1.setBounds(291, 173, 70, 24);
		ButtonGroup bg1 = new ButtonGroup();

		bg1.add(rdbtnNewRadioButton_1);
		bg1.add(rdbtnNewRadioButton);

		btnStartDownloads = new JButton("Start Downloads");
		btnStartDownloads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					startscan();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnStartDownloads.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 14));
		btnStartDownloads.setBounds(190, 215, 171, 26);
		JLabel lblNewLabel = new JLabel("Ports");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		lblNewLabel.setBounds(190, 177, 51, 16);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(progressBar);
		frame.getContentPane().add(btnStartDownloads);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(rdbtnNewRadioButton);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton.setEnabled(false);
		rdbtnNewRadioButton_1.setEnabled(false);
		frame.getContentPane().add(label1);

		txtEnterHostTo = new JTextField();
		txtEnterHostTo.setBounds(190, 142, 171, 20);
		txtEnterHostTo.setForeground(Color.LIGHT_GRAY);
		txtEnterHostTo.setText("Enter Host to Scan");
		frame.getContentPane().add(txtEnterHostTo);
		txtEnterHostTo.setColumns(10);
		txtEnterHostTo.setEnabled(false);

		btnNewButton = new JButton("Show Logs");
		logs window1 = new logs();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					btnNewButton.setEnabled(false);
					window1.main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 12));
		btnNewButton.setBounds(230, 258, 98, 26);
		frame.getContentPane().add(btnNewButton);

	}

	public static void start() throws Exception {
		Runnable runner9 = new Runnable() {
			public void run() {
				try {
					btnStartDownloads.setEnabled(false);
					Main.main(null);
					btnStartDownloads.setEnabled(true);
					rdbtnNewRadioButton.setEnabled(true);
					rdbtnNewRadioButton_1.setEnabled(true);
					txtEnterHostTo.setEnabled(true);
					progressBar.setEnabled(false);
					btnStartDownloads.setText("Start Scan");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread t9 = new Thread(runner9, "Code Executer");
		t9.start();

	}

	public static void startscan() throws Exception {

		Runnable runner7 = new Runnable() {
			public void run() {
				try {

					btnStartDownloads.setEnabled(false);
					String para="";
					if(rdbtnNewRadioButton_1.isSelected())
						para="-F";
					else
						para="-p-";
					String input = txtEnterHostTo.getText();
					startScan sc = new startScan();
					sc.scan(tempDir, input,para);
					// Main.deleteDirectory(Main.dir);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread t7 = new Thread(runner7, "Code Executer");
		t7.start();
	}
}

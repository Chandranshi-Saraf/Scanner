package com.instasafe.nmapscanner;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.WindowEvent;  
import java.awt.event.WindowListener;  
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class logs {

	private JFrame frame;
	private ImageIcon image;
	private static JLabel label1;
	 static String property = "java.io.tmpdir";
	 static String tempDir = System.getProperty(property);
		static logPanel log;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logs window = new logs();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public logs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 666, 383);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(tempDir + "scan/instasafe-icon.png"));
		frame.setBounds(100, 100, 591, 383);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		image= new ImageIcon(getClass().getResource("/resources/instasafe-icon.png"));
		label1=new JLabel(image);
		label1.setBounds(0, 0, 122, 39);
		frame.getContentPane().add(label1);
		log= new logPanel();
		log.setEnabled(true);
		log.setBounds(0, 74, 765, 470);
		frame.getContentPane().add(log);
		frame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 startHere.btnNewButton.setEnabled(true);
	            frame.dispose();
	           
	         }        
	      });
	}

}

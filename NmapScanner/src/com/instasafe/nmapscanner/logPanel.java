package com.instasafe.nmapscanner;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class logPanel extends JPanel {

	static JTextArea textArea;
	public logPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 50, 589, 215);
		add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		scrollPane.setAutoscrolls(isEnabled());
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setEditable(false);
		textArea.setAutoscrolls(getFocusTraversalKeysEnabled());
		
		JLabel lblNewLabel = new JLabel("Console Output");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.ITALIC, 14));
		lblNewLabel.setBounds(230, 10, 150, 14);
		add(lblNewLabel);

	}
}
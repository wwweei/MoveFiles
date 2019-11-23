package com.wuchen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class SingleButtonActionLisener implements ActionListener {
	private JTextField targetPathField ;
	private JTextField movePathField ;
	
	public SingleButtonActionLisener(JTextField targetPathField, JTextField movePathField) {
		this.targetPathField = targetPathField;
		this.movePathField = movePathField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String targetPath = targetPathField.getText();
		String movePath = movePathField.getText();
		System.out.println(targetPath);
		System.out.println(movePath);
		
		SingleMoveFile sf = new SingleMoveFile();
		
		sf.StartMoveFiles(targetPath, movePath);
		
	}

}

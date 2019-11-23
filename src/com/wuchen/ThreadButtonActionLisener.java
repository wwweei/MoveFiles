package com.wuchen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ThreadButtonActionLisener implements ActionListener {
	private JTextField targetPathField ;
	private JTextField movePathField ;
	
	public ThreadButtonActionLisener(JTextField targetPathField, JTextField movePathField) {
		this.targetPathField = targetPathField;
		this.movePathField = movePathField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (JOptionPane.showConfirmDialog(null, "这个多线程我可能理解有问题，这个复制逻辑问题有点大，确定要用？", "不好使", JOptionPane.ERROR_MESSAGE) == 1) 
			return ;
		
		String targetPath = targetPathField.getText();
		String movePath = movePathField.getText();
		System.out.println(targetPath);
		System.out.println(movePath);
		
		ThreadMoveFile tmf = new ThreadMoveFile();
		
		tmf.StartMoveFiles(targetPath, movePath);
		
	}

}

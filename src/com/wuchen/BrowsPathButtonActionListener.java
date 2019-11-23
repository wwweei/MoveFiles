package com.wuchen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class BrowsPathButtonActionListener implements ActionListener {
	private JTextField pathField;

	public BrowsPathButtonActionListener(JTextField pathField) {
		this.pathField = pathField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame targetPathWindow = new JFrame();
		JFileChooser targetPathChooser = new JFileChooser();
		targetPathChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//只能选择目录
		int flag = targetPathChooser.showOpenDialog(targetPathWindow);
		File f=null;
		String path= "";
		targetPathWindow.add(targetPathChooser);
		targetPathWindow.setVisible(true);
		if(flag == JFileChooser.APPROVE_OPTION){
			//获得该文件
			f = targetPathChooser.getSelectedFile();
			path=f.getPath();
		}
		
		pathField.setText(path);

	}

}

package com.wuchen;
import java.awt.Color;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WindowFrame {

	public static void main(String[] args) throws UnsupportedEncodingException {
		JFrame window = new JFrame("复制文件");
		window.setBounds(60,100,500,500);
		window.getContentPane().setBackground(Color.white);
		window.setBackground(Color.white);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		JLabel targetPathLabel = new JLabel();
		targetPathLabel.setText("请选择需要移动的路径");
		targetPathLabel.setBounds(20, 20, 140, 20);
		targetPathLabel.setOpaque(true);
		targetPathLabel.setBackground(Color.white);
		window.add(targetPathLabel);
		
		JLabel movePathLabel = new JLabel();
		movePathLabel.setText("需要复制到");
		movePathLabel.setOpaque(true);
		movePathLabel.setBackground(Color.white);
		movePathLabel.setBounds(20, 50, 140, 20);
		window.add(movePathLabel);
		
		JTextField targetPathField = new JTextField();
		targetPathField.setOpaque(true);
		targetPathField.setBounds(160, 20, 140, 20);
		targetPathField.setEditable(false);
		window.add(targetPathField);
		
		JTextField movePathField = new JTextField();
		movePathField.setOpaque(true);
		movePathField.setBounds(160, 50, 140, 20);
		movePathField.setEditable(false);
		window.add(movePathField);
		
		JButton singleStart = new JButton();
		singleStart.setText("单线程复制");
		singleStart.setBounds(100, 100, 140, 20);
		window.add(singleStart);
		singleStart.addActionListener(new SingleButtonActionLisener(targetPathField, movePathField));
		
		JButton threadStart = new JButton();
		threadStart.setText("多线程复制");
		threadStart.setBounds(300, 100, 140, 20);
		window.add(threadStart);
		threadStart.addActionListener(new ThreadButtonActionLisener(targetPathField, movePathField));
		
		JLabel msgLabel = new JLabel();
		msgLabel.setText("复制详情");
		msgLabel.setOpaque(true);
		msgLabel.setBackground(Color.white);
		msgLabel.setBounds(80, 150, 140, 20);
		window.add(msgLabel);
		
		JTextField msgWindow = new JTextField();
		msgWindow.setOpaque(true);
		msgWindow.setBounds(80, 180, 320, 200);
		msgWindow.setEditable(false);
		window.add(msgWindow);
		
		
		JButton targetPathButton = new JButton("brows");
		targetPathButton.setBounds(300, 20, 140, 20);
		window.add(targetPathButton);
		targetPathButton.addActionListener(new BrowsPathButtonActionListener(targetPathField));
		
		JButton movePathButton = new JButton("brows");
		movePathButton.setBounds(300, 50, 140, 20);
		window.add(movePathButton);
		movePathButton.addActionListener(new BrowsPathButtonActionListener(movePathField));
		
		window.setVisible(true);
	}
	
}

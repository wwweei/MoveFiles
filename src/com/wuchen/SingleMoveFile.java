package com.wuchen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleMoveFile {
	
	public void StartMoveFiles(String targetPath, String movePath) {
		//要移动的目标文件
//		String targetPath = targetPath;
		//目的地
//		String movePath = movePath;
		
		File targetFile = new File(targetPath);
		
		//存放该文件夹下所有文件
		List<File> fileList  = new ArrayList<File>();
		
		if (targetFile.isDirectory()) {
			//targetFile.list();    //返回值为String[] 返回该路径下文件名称
			
			//获取改路径下所有文件
			File[] targetFIleList =  targetFile.listFiles();
			
			getFileList(new ArrayList<File> (Arrays.asList(targetFIleList)), fileList);
			long start = System.currentTimeMillis();
			for(File file : fileList) {
				moveFile(file, targetPath, movePath);
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		} else {
			System.out.println("this path not a directory");
			return ;
		}
		
		System.out.println("move over");
	}
	
	public void getFileList(List<File> files, List<File> fileList) {

		while(files.size() != 0) {
			//判断files中的指向是否为文件，是文件则放入fileList，不是则该指向的路径下所有文件放入files
			if( ( (File) files.get(0) ).isDirectory() ) {
				files.addAll(new ArrayList<File>( Arrays.asList(((File) files.get(0)).listFiles() )));
				
			} else {
				fileList.add(files.get(0));
				
			}
			//删除已经判断过的指向
			files.remove(0);
		}
	}
	
	
	public void moveFile(File file, String targetPath, String movePath) {
		String fileName = file.getName();
		
		
		//防止所有文件放入同一文件夹下可能重名的问题，取最多前两个节点的名称拼接至文件名
		String parentPath = file.getParent();
		parentPath = parentPath.replace(File.separator, "-");
		String[] parentDirectArr = parentPath.split("-");
		String parentName = "";
		if (parentDirectArr.length > 2) {
			parentName = parentDirectArr[parentDirectArr.length - 2] + "-" + parentDirectArr[parentDirectArr.length - 1];
		} else if(parentDirectArr.length == 2){
			parentName = parentDirectArr[parentDirectArr.length - 1];
		}
		String moveFileName = "".equals(parentName) ? "" + fileName : parentName + "-" + fileName;
		File targetFile = new File(movePath + File.separator + moveFileName);
		
		
		try {
			targetFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream is = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream os;
		try {
			is = new FileInputStream(file);
			bis = new BufferedInputStream(is);
			
			os = new FileOutputStream(targetFile);
			bos = new BufferedOutputStream(os);
			byte[] buff = new byte[1024] ;
			while (bis.read(buff) != -1) {
				bos.write(buff);
			}
			bos.flush();
			System.out.println(targetFile.getPath()+"------------复制完成");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
}

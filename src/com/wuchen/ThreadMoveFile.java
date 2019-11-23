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

public class ThreadMoveFile {
	public void StartMoveFiles(String targetPath, String movePath) {
		//要移动的目标文件
		//String targetPath = "L:" + File.separator + "补丁";
		//目的地
		//String movePath = "L:"+File.separator+"bbb";
		File targetFile = new File(targetPath);
		//存放该文件夹下所有文件
		List<File> fileList  = new ArrayList<File>();
		
		if (targetFile.isDirectory()) {
			//targetFile.list();    //返回值为String[] 返回该路径下文件名称
			
			//获取改路径下所有文件
			List<File> targetFIleList =  new ArrayList<File> (Arrays.asList(targetFile.listFiles()));
			
			//生产所有文件的名称
			getFileList(targetFIleList, fileList);
			long start = System.currentTimeMillis();
			
			new Thread(()-> {
				System.out.println("Thread-1:"+"start");
				moveFile(fileList, targetPath, movePath,"Thread-1");
				long end = System.currentTimeMillis();
				System.out.println("Thread-1:"+"move over");
				System.out.println("Thread-1:"+ (end - start));
			}).start();
			
			new Thread(()-> {
				System.out.println("Thread-2:"+"start");
				moveFile(fileList, targetPath, movePath, "Thread-2");
				long end = System.currentTimeMillis();
				System.out.println("Thread-2:"+"move over");
				System.out.println("Thread-2:"+ (end - start));
			}).start();
			
			new Thread(()-> {
				System.out.println("Thread-3:"+"start");
				moveFile(fileList, targetPath, movePath, "Thread-3");
				long end = System.currentTimeMillis();
				System.out.println("Thread-3:"+"move over");
				System.out.println("Thread-3:"+ (end - start));
			}).start();
			
			new Thread(()-> {
				System.out.println("Thread-4:"+"start");
				moveFile(fileList, targetPath, movePath, "Thread-4");
				long end = System.currentTimeMillis();
				System.out.println("Thread-4:"+"move over");
				System.out.println("Thread-4:"+ (end - start));
			}).start();
			
		} else {
			System.out.println("this path not a directory");
			return ;
		}
		
		
	}
	
	public void getFileList(List<File> files, List<File> fileList) {

		
		while(files.size() != 0) {
			if( ( (File) files.get(0) ).isDirectory() ) {
				
				files.addAll(new ArrayList<File>( Arrays.asList(((File) files.get(0)).listFiles() )));
				
			} else {
				fileList.add(files.get(0));
				
			}
			files.remove(0);
		}
	}
	
	
	public void moveFile(List<File> fileList, String targetPath, String movePath, String threadName) {
		
		while (fileList.size()!=0) {
			File file;
			synchronized (fileList) {
				file = new File(fileList.get(0).toString());
				fileList.remove(0);
			}
			String fileName = file.getName();
			
			System.out.println(threadName+": copy start" + "---" + fileName);
			
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
				// TODO Auto-generated catch block
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
				System.out.println(threadName+":"+targetFile+"-------复制完成");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					bis.close();
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
}

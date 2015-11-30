package FileOperation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class WriteFile {
	
	//写txt文本，参数为：文本字符串内容、文件名(包括路径)、文本编码方式；
	public void writeFile(String fileContent, String fileName, String type, String coding){

		if(type.contains(".")) 
			fileName = fileName + type;
		else 
			fileName = fileName + "." + type;
		
		File file = new File(fileName);
	//	if(file.exists()){//如果存在该文件，则删之
	//		file.delete();
	//	}
	//	else{
	//		try{
	//				file.createNewFile();
	//		   }catch (IOException e){e.printStackTrace();}
	//	}
		
		if( fileContent == null || fileContent.length() == 0){
			System.out.println("the file is null!");
			return;
		}
		
		try{
			byte[] buf = ByteString.toBytes(fileContent, coding);//调用ByteString类的toBytes方法
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(buf);
			fos.close();
		}catch(Exception e){e.printStackTrace();}
	}
	/*
	/////////////////////////////////////////////////////////////////////////
	//写txt文本，参数为：文本字符串内容、文件名、文本编码方式；  增加了加密功能   //
	////////////////////////////////////////////////////////////////////////
	public static void writeBinaryFile(String fileContent, String fileName, String type, String coding){

		if(type.contains(".")) fileName = fileName + type;
		else fileName = fileName + "." + type;
		
		File file = new File(fileName);
		if(file.exists()){//如果存在该文件，则删之
			file.delete();
		}
		else{
			try{
					file.createNewFile();
			   }catch (IOException e){e.printStackTrace();}
		}
		
		if( fileContent == null || fileContent.length() == 0){
			System.out.println("the file is null!");
			return;
		}
		
		try{
			fileContent = MD5andKL.JIAMI(fileContent);//加密
			
			byte[] buf = ByteString.toBytes(fileContent, coding);//调用ByteString类的toBytes方法
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(buf);
			fos.close();
			//FileWriter writer = new FileWriter( fileName );//创建一个给定文件名的输出流对象
			//writer.write(new String(buf, coding));   //往流里写字符数组
			//writer.close();                           //关闭流
		}catch(Exception e){e.printStackTrace();}
	}*/
}

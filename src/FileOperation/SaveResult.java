package FileOperation;

import java.io.File;

import FileOperation.WriteFile;

/**
 * 保存分类结果：
 * 1、分类结果保存在根目录：E:\分类结果   文件夹下
 * 2、在根目录下以类别名称命名类别子文件夹
 * 3、将源文件用  “类别名+日期+出处” 命名，保存在相应类别的子文件夹下
 * */
public class SaveResult {
	
	
	//保存分类结果；参数为：根目录名、子目录名、文件名、文件内容、编码方式
	//如：parentPath="E:\\分类结果"; childPath = "5经济类"; 
	//    fileName="经济类2010-02-12青海广播网"; fileContent="..."; coding="GBK";
	public static void saveResult(String parentPath, String childPath, String fileName,
			               String fileContent, String type, String coding)//throws Exception
	{
		File path1 = new File(parentPath);
		File path2 = new File(parentPath+"\\"+childPath); //注意要这样写！！！
		
		//创建根目录
		if(!path1.exists() && !path1.isFile()){
			path1.mkdir();
		}
		//创建子目录
		if(!path2.exists() && !path2.isFile()){
			path2.mkdir();
		}
		//将该文件用 ‘类别名称+日期+出处’ 命名，保存在上述文件夹下
		WriteFile writeFile = new WriteFile();
		writeFile.writeFile(fileContent, parentPath+"\\"+childPath+"\\"+fileName, type, coding);
	}
	

	/*
	public static void main(String[] args) throws Exception
	{
		String savePath = "E:\\分类结果";
		String fileName = "政治类2010-10-3青海广播网.txt";
		
		File file = new File(filePath);
		if(!file.exists() && !file.isFile()){
			file.mkdir();
		}
	}*/
}
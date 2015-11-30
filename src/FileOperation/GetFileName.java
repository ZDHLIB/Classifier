package FileOperation;

import java.io.File;
import java.util.LinkedList;

public class GetFileName {
	
	/**
	 * 功能：     获取一个文件夹下的所有文件的名称(包括其中子文件夹的名称)
	 * 参数：     filePathName 必须是一个文件夹的路径,如：D:\html
	 * 返回值：(1) null      表示无任何文件或出错
	 * 		   (2) String[] 文件名数组(至少包含一个文件名)
	 */
	public static String[] getAllFileName(String filePathName)
	{
		File file = new File(filePathName);
		if( !file.isDirectory() ){         //如果参数filePathName不是路径
			System.out.println("error from myapi.GetFileName.getFileName:"+filePathName+"不是一个路径!");
			return null;
		}
		
		File[] fileList = file.listFiles();
		if(fileList==null || fileList.length==0){
			System.out.println("error from myapi.GetFileName.getFileName:无法获取目录"+filePathName+"下的文件名,或该目录下无任何文件!");
			return null;
		}
		
		String[] fileNameList = new String[fileList.length];
		
		for(int i=0; i<fileList.length; i++){
			fileNameList[i] = fileList[i].getName();
		}
		return fileNameList;
	}
	
	/**
	 * 功能：     求目录及此目录下的所有文件的名称(绝对路径名)
	 * 参数：     filePathName 必须是一个文件夹的路径,如：D:\html
	 * 返回值：(1) null      表示无任何文件或出错
	 * 		   (2) String[] 文件名数组(至少包含一个文件名)
	 */
	
	public static String[] getAllFileName_2(String filePathName)
	{
	   String fileNamesStr = getFileNameStr(new File(filePathName));
	   return fileNamesStr.split("\r\n");
    }  

	private static String getFileNameStr(File file){
		//判断文件是否存在  
		if (file.exists()) {  
		      if(!file.isFile()){    
	             File[] fl = file.listFiles();
	             String tmp = "";
	             for (File f : fl)  
	            	 tmp += getFileNameStr(f) + "\r\n";
	              return tmp;  
	          }else{ 
	             return file.getAbsolutePath();  
	           }  
	    }else{  
	          System.out.println("文件或者文件夹不存在，请检查路径是否正确！");  
		      return null;  
	    }  
	}
	
	
	/**
	 * 功能：      获取一个文件夹下指定格式的文件名列表
	 * 参数：      filePathName  一个文件夹的路径
	 * 	       fileType       文件格式，如'html' 或  'htm' 或 'txt'
	 *  
	 * 返回值：(1)null     表示接收到的参数有错误
	 * 	      (2)String[]  满足指定格式的文件名列表(最少包含一个文件)
	 */
	public static String[] getFileName(String filePathName, String []fileType)
	{
		File file = new File(filePathName);
		if( !file.isDirectory() ){         //如果参数filePathName不是路径
			System.out.println("error from myapi.GetFileName.getFileName:"+filePathName+"不是一个路径!");
			return null;
		}
		if(fileType==null || fileType.length==0){
			System.out.println("error from myapi.GetFileName.getFileName:"+filePathName+"文件类型指定有误!");
			return null;
		}
		
		//如果参数合法，则继续下面的操作
		File[] fileNameList = file.listFiles();           //获取该目录下所有文件的文件名列表
		if(fileNameList==null || fileNameList.length==0){
			System.out.println("error from myapi.GetFileName.getFileName:无法获取目录"+filePathName+"下的文件名,或该目录下无任何文件!");
			return null;
		}
		//获取指定格式的文件名
		LinkedList<String> fileNames = new LinkedList<String>();  //保存指定格式的文件名
		String type="";
		for(int i=0; i<fileNameList.length; i++){
			type = fileNameList[i].getName();
			if( !type.contains(".") ) continue;
			type = type.substring(type.lastIndexOf('.')+1, type.length());  //获取文件名后缀(即文件格式)
			for(int j=0; j<fileType.length; j++){
				if(fileType[j].equalsIgnoreCase(type)){                     //不区分大小写
					fileNames.add(fileNameList[i].getName());
				}
			}
		}
		//结果返回
		if(fileNames.size()==0){
			System.out.println("error from myapi.GetFileName.getFileName:目录"+filePathName+"下无指定格式的文件!");
			return null;
		}else {
			String []tmp = new String[fileNames.size()];
			for(int i=0; i<tmp.length; i++){
				tmp[i] = fileNames.get(i);
			}
			return tmp;
		}
	}
	
	//======================================================================
	public static void main(String[] args)
	{
		String path =  ".\\configureFile_WebSite";
		String[] names =  getAllFileName_2(path);
		if(names!=null){
			for(int i=0; i<names.length; i++){
				System.out.println(names[i]);
			}
		}
	}
}
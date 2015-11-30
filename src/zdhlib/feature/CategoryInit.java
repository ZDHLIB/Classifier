package zdhlib.feature;

import java.io.File;
import java.util.ArrayList;

public class CategoryInit {

	private ArrayList<String> categories = new ArrayList<String>();
	private ArrayList<Integer> categoryFileNum = new ArrayList<Integer>();
	
	public ArrayList<String> getCategories() {
		return categories;
	}


	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}


	public ArrayList<Integer> getCategoryFileNum() {
		return categoryFileNum;
	}


	public void setCategoryFileNum(ArrayList<Integer> categoryFileNum) {
		this.categoryFileNum = categoryFileNum;
	}

	public CategoryInit(String filePath){
		getTrainCategories(filePath);
	}

	/**
	 * 获取所有类别和每个类别的文档数，训练语聊的文件夹用类别命名
	 * @author zdhlib
	 * @param filePath
	 * @return ArrayList<String> categories
	 */
	public void getTrainCategories(String filePath){
		try{
			
			File file = new File(filePath);
			if( !file.isDirectory() ){         //如果参数filePathName不是路径
				System.out.println("error from myapi.GetFileName.getFileName:"+filePath+"不是一个路径!");
				return ;
			}
			
			File[] fileList = file.listFiles();
			if(fileList==null || fileList.length==0){
				System.out.println("error from myapi.GetFileName.getFileName:无法获取目录"+filePath+"下的文件名,或该目录下无任何文件!");
				return;
			}
			
			for(int i=0; i<fileList.length; i++){
				categories.add( fileList[i].getName() );
				categoryFileNum.add( fileList[i].listFiles().length );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

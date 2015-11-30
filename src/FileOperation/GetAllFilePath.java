package FileOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetAllFilePath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> pathList = new ArrayList<String>();
		ArrayList<ArrayList<String>> randomPathList = new ArrayList<ArrayList<String>>();
		GetAllFilePath test = new GetAllFilePath();
		String filePath = "E:\\JAVA\\ProgramFiles\\ZZ_FileSeperate_TestFiles\\SourceFiles\\Sample2";
		
		pathList = test.getAllFilePath(filePath, pathList);
//		System.out.println("aaaaaaaaaaaaaaaaaaaa"+pathList.size());
		randomPathList = test.getRandomFilePath(pathList, randomPathList);
		System.out.println(randomPathList.get(0).size());
		for( int i = 0; i < randomPathList.get(0).size(); i++ ) {
			System.out.println(randomPathList.get(0).get(i));
		}
	}
	
	/*
	 * @function: Get all files' path under a directory's path
	 */
	public List<String> getAllFilePath(String dirPath, List<String> pathList) {
		
		File file = new File(dirPath);
		
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				pathList.add(file.getAbsolutePath()); 
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					getAllFilePath(files[i].getAbsolutePath(),pathList); // 把每个文件 用这个方法进行迭代
				}
			}
		} else {
			System.out.println("执行getAllFilePath时文件不存在！" + '\n');
		}
		return pathList;
	}
	
	/**
	 * @function: Get 4/5 and 1/5 files of all paths
	 * @return: ArrayList<ArrayList<String>> randomList: randomList(1)==trainPaths, randomList(2)==testPath
	 */
	public ArrayList<ArrayList<String>> getRandomFilePath( List<String> pathList, ArrayList<ArrayList<String>> randomList ){
		
		boolean flag = false;
		int temp = 0;
		int m = 0;
		long tick = System.currentTimeMillis();
		ArrayList<String> testList = new ArrayList<String>();
		ArrayList<String> trainList = new ArrayList<String>();
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		Random ran = new Random( (int)(tick &0xffffffffL) | (int)(tick>>32));
		
		m = pathList.size() / 5;
		while(tempList.size()<m){
			for( int i = 0; i < pathList.size()-m+1; i+=m ){
				temp = ran.nextInt(m) + i;
				System.out.println("random temp = "+temp);
				if( !tempList.contains(temp)){
					tempList.add(temp);
				}
				testList.add(pathList.get(temp));
				if(tempList.size()==m){
					break;
				}
			}
		}
			
		
		System.out.println(tempList.size());
		
		for( int i = 0; i < pathList.size(); i++ ){
			if( !testList.contains(pathList.get(i))){
				trainList.add(pathList.get(i));
			}
		}
		
		randomList.add(trainList);
		randomList.add(testList);
		
		return randomList;
	}

}

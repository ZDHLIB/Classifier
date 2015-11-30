package zdhlib.feature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;

public class Words {

	static private ArrayList<String> words = new ArrayList<String>();
	static private ArrayList<String> wordstemp = new ArrayList<String>();
	static private HashSet<String> wordSet = new HashSet<String>();
	static private HashSet<String> stoplist = new HashSet<String>();
	
	public Words( String stoplistFilePath ){
		getStopList( stoplistFilePath );
	}
	
	public HashSet<String> getWordSet(){
		return wordSet;
	}
	
	public ArrayList<String> getWords(){
		return words;
	}
	
	/**
	 * 返回待测文章的特征词（去除停用词后）
	 * @author zdhlib
	 * @param AnalysisFilePath
	 * @return ArrayList<String> words
	 */
	public HashSet<String> getWords(String AnalysisFilePath) {
		try{
			//System.out.println(wordstemp.size());
			getAnalysisWords(AnalysisFilePath);
			for( int i = 0; i < wordstemp.size(); i++ ){
				//System.out.println(wordstemp.get(i));
				if( stoplist.contains(wordstemp.get(i)) ){
					//System.out.println("111"+wordstemp.get(i));
					wordstemp.set(i, null);
				}else{
					//System.out.println(wordstemp.get(i));
					words.add(wordstemp.get(i));
					wordSet.add(wordstemp.get(i));
				}
			}
			wordstemp.clear();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return wordSet;
	}
	
	/**
	 * 获取停用词
	 * @author zdhlib
	 * @param stoplistFilePath
	 * @return null
	 */
	public void getStopList(String stoplistFilePath){
		File f = null; 
		BufferedReader reader = null;
		String tempString = null;
		try{
			f = new File(stoplistFilePath);
			reader = new BufferedReader(new FileReader(f));
			
			while ((tempString = reader.readLine()) != null) {
				stoplist.add(tempString);
            }			
			reader.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取停用词
	 * @author zdhlib
	 * @param AnalysisFilePath
	 * @return null
	 */
	public void getAnalysisWords(String AnalysisFilePath){
		File f = null; 
		BufferedReader reader = null;
		String tempString = null;
		SegTag segTag = new SegTag(1);
		SegResult seg_res =  null;
		try{
			f = new File(AnalysisFilePath);
			reader = new BufferedReader(new FileReader(f));
			while ((tempString = reader.readLine()) != null) {
				seg_res = segTag.split(tempString);
				tempString = seg_res.getFinalResult();
				//System.out.println(tempString);
				String [] splitResult = tempString.split(" ");
				for( int i = 0; i < splitResult.length; i++ ){
					wordstemp.add(splitResult[i]);
				}
            }
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Words words = new Words("TrainFile", "Data/StopList.txt");
		ArrayList<String> wordstest = new ArrayList<String>();
		
		//wordstest = words.getWords("TrainFile", "Data/StopList.txt");
		//for( int i = 0; i < wordstest.size(); i++ )
			//System.out.println(wordstest.get(i));
	}

}

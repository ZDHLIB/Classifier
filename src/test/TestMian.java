package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ictclas4j.bean.MidResult;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;

import FileOperation.AppendToFile;

import zdhlib.feature.Feature;
import zdhlib.feature.FeatureCount;

public class TestMian {

	static class ByValueComparator implements Comparator<String> {
        HashMap<String, Float> base_map;
 
        public ByValueComparator(HashMap<String, Float> base_map) {
            this.base_map = base_map;
        }
 
        public int compare(String arg0, String arg1) {
            if (!base_map.containsKey(arg0) || !base_map.containsKey(arg1)) {
                return 0;
            }
 
            if (base_map.get(arg0) < base_map.get(arg1)) {
                return 1;
            } else if (base_map.get(arg0) == base_map.get(arg1)) {
                return 0;
            } else {
                return -1;
            }
        }
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
//			ArrayList<String> wordstmp = new ArrayList<String>();
//			ArrayList<String> words = new ArrayList<String>();
//			String tempString = null;
//			String segResult = "";
//			ArrayList<String> stopList = new ArrayList<String>();
//			SegTag segTag = new SegTag(1);
//			
//			SegResult seg_res = segTag.split("我想自己试一试，看看可不可以！");
//			segResult = seg_res.getFinalResult();
//			System.out.println(segResult);
//			String [] splitResult = segResult.split(" ");
//			
//			for( int i = 0; i < splitResult.length; i++ ){
//				wordstmp.add(splitResult[i]);
//			}
//			
//			////////////////读取停用词表//////////////////////////
//			File f = new File("Data/StopList.txt");
//			BufferedReader reader = new BufferedReader(new FileReader(f));
//			
//			while ((tempString = reader.readLine()) != null) {
//                // 显示行号
//				stopList.add(tempString);
//            }			
//			reader.close();
//			/////////////////////////////////////////////////////
//			
//			for( int i = 0; i < wordstmp.size(); i++ ){
//				if( stopList.contains(wordstmp.get(i)) ){
//					wordstmp.set(i, null);
//				}else{
//					words.add(wordstmp.get(i));
//				}
//			}
//			wordstmp.clear();
//			
//
//			for( int i = 0; i < words.size(); i++ )
//				System.out.println(words.get(i));
			HashMap<String, HashMap<String, Float> > statistic = new HashMap<String, HashMap<String, Float> >();
			ArrayList<Feature> categoryFeature = new ArrayList<Feature>();
			FeatureCount test = new FeatureCount();
			AppendToFile append = new AppendToFile();
			
			test.FeatureCounter("TrainFile", "Data/StopList.txt");
			test.FeatureChiSquare("TrainFile");
			statistic = test.getStatistic();
			
			Iterator it = statistic.entrySet().iterator();
			while( it.hasNext() ){
				
				System.out.println("=============================================================");
				append.appendMethodA("featureResult.txt", "=============================================================\n");
				Feature feature = new Feature();
				Map.Entry<String, HashMap<String, Float> > ob = (Entry<String, HashMap<String, Float> >) it.next();
				
				String node = ob.getKey();
				append.appendMethodA("featureResult.txt", node+"\n");
				//System.out.println("类别："+node);
				
				feature.setCategory(node);
				
				ByValueComparator bvc = new ByValueComparator(ob.getValue());
				List<String> keys = new ArrayList<String>(ob.getValue().keySet());
		        Collections.sort(keys, bvc);
		        int n = 0;
		        for(String key : keys) {
		        	if( n >= 20 )
		        		break;
		        	feature.getFeature().put(key, ob.getValue().get(key));
					append.appendMethodA("featureResult.txt", "特征：           "+key+"\n");
					append.appendMethodA("featureResult.txt", "卡方值：      "+ob.getValue().get(key)+"\n");
		        	//System.out.println("特征："+key);
					//System.out.println("卡方值："+ ob.getValue().get(key));	
		        }
		        
		        categoryFeature.add(feature);

				System.out.println("=============================================================");
			}
			
			System.out.print("dd");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}

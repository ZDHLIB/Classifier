package zdhlib.feature;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class FeatureCount {

	private static HashMap<String, int[]> feature = new HashMap<String, int[]>();  //String:�ʣ�Integer[]: ÿ�������ָĴʵ��ĵ���
	private static HashMap<String, HashMap<String, Float> > statistic = new HashMap<String, HashMap<String, Float> >();
		
	

	public HashMap<String, HashMap<String, Float>> getStatistic() {
		return this.statistic;
	}


	/**
	 * ͳ��ÿ�����ڲ�ͬ����У��ж������³��ֹ�
	 * @param filePath
	 * @param stoplistFilePath
	 */
	public void FeatureCounter(String filePath, String stoplistFilePath){
		Words words = new Words(stoplistFilePath);
		ArrayList<String> wordFilter = new ArrayList<String>();
		HashSet<String> wordSet = new HashSet<String>();
		try{
			
			File file = new File(filePath);
			if( !file.isDirectory() ){         //�������filePathName����·��
				System.out.println("error from myapi.GetFileName.getFileName:"+filePath+"����һ��·��!");
				return ;
			}
			
			File[] fileList = file.listFiles();
			if(fileList==null || fileList.length==0){
				System.out.println("error from myapi.GetFileName.getFileName:�޷���ȡĿ¼"+filePath+"�µ��ļ���,���Ŀ¼�����κ��ļ�!");
				return;
			}
			
			
			Integer flag = 0;
			for(int i = 0; i < fileList.length; i++){
				System.out.println("���ڴ������"+fileList[i].getName()+"��������Ϣ...");
				File subFile[] = fileList[i].listFiles();
				for( int j = 0; j < subFile.length; j++ ){
					//��ȡһƪ���µ���������
					wordSet = words.getWords(subFile[j].getAbsolutePath());
					//����ƪ����������������ͳ��
					Iterator it = wordSet.iterator();
					while( it.hasNext() ){
						String tmp = (String) it.next();
						if( !feature.containsKey( tmp ) ){
							//��һ�γ��֣�����feature�У�������Ӧ�����������+1
							int count[] = new int[500];
							count[i]++;
							feature.put( tmp, count );
						}else{
							int count[] = feature.get( tmp );
							count[i]++;
							feature.put( tmp, count);
						}
					}					
				}
				wordSet.clear();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	public void FeatureChiSquare(String filePath){
		
	
		try{
			
			CategoryInit category = new CategoryInit(filePath);
			ArrayList<String> categories = category.getCategories();
			ArrayList<Integer> categoryFileNum = category.getCategoryFileNum();
						
			int numCategory = categories.size();
			Float f = 0.0f;
			
			for( int i = 0; i < numCategory; i++ ) {
				System.out.println("�������"+categories.get(i)+"�����������Ŀ���ֵ");
				Iterator it = feature.keySet().iterator();
				HashMap<String, Float> tmp = new HashMap<String, Float>();
				while( it.hasNext() ){
					String node = (String) it.next();
					int count[] = feature.get(node);
					
					float A = (float)count[i];
					float B = (float) 0.0;
					for( int k = 0; k < numCategory; k++ )
						if( k != i )
							B += (float)count[k];
					float C = ((float)categoryFileNum.get(i) - A);
					float D = (float)0.0;
					for( int k = 0; k < numCategory; k++ ) {
						D += (float)categoryFileNum.get(k);
					}
					D = D - (float)categoryFileNum.get(i) - B;
					
					f = ( A*D - B*C )*( A*D - B*C ) / ( (A + B)*(C + D) );
					
					tmp.put(node, f);
				}
				statistic.put( categories.get(i), tmp );
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

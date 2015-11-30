package FileOperation;

public class ByteString {
	
	//将'字节数组'按照给定的编码方式构造成'字符串'
	public static String toSting(byte[] bytes, String coding)
	{
		String resultString=null;
		
		if(coding == "" || coding == null)return new String(bytes);
		
		try{
				if (coding.equals("UTF-8")) {
					resultString=new String(bytes,3,bytes.length-3,coding);
				} 
				else if (coding.equals("UTF-16LE")) {
					resultString=new String(bytes,2,bytes.length-2,coding);
				} 
				else {
					resultString=new String(bytes,coding);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultString;
	}

	//将字符串按照给定的编码方式解析成字节数组
	public static byte[] toBytes(String content, String charsetName)
	{
		byte[] buf = null;
		
		if(charsetName == "" || charsetName == null) return content.getBytes();
			
		try{
				if(charsetName.equals("UTF-16LE")){
					byte b2[]=content.getBytes(charsetName);
					buf=new byte[2+b2.length];
					int i=2;
					buf[0]=(byte)255; //ff
					buf[1]=(byte)254; //fe
					for(int j=0;j<b2.length;j++){
						buf[i++]=b2[j];
					}
				}
				else if(charsetName.equals("UTF-8")){	
					byte b2[]=content.getBytes(charsetName);
					buf=new byte[3+b2.length];
					int i=3;
					buf[0]=(byte)239;  //ef
					buf[1]=(byte)187;  //bb
					buf[2]=(byte)191;  //bf
					for(int j=0;j<b2.length;j++){
						buf[i++]=b2[j];
					}
				}
				else{
					buf=content.getBytes(charsetName);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return buf;
	}
}

package FileOperation;

public class Utf8ToUnicode {

	 /** 
	    * 将utf-8编码转化为unicode编码 
	    * @param aByte byte[] -原字符串的utf-8编码字节数组 
	    * return sByte byte[] -转化后的unicode编码方式的字符串
	    */ 
	    public static String changeUtf8ToUnicode(byte[] aByte) { 
	       int sLength = aByte.length; //原字节数组长度 
	       //存储转化为unicode编码后的StringBuffer字符串 
	       StringBuffer sUnicodeStringBuffer = new StringBuffer(); 
	       char sChar; //用于临时存放每个从utf-8中解析出来的unicode编码 
	       //以下操作是判断字节是否以"1110 xxxx 10xxxxxx 10xxxxxx"的形式出现 
	       for (int i = 0; i < sLength; i++) { //循环每一个字节 
	           if (i + 2 < sLength) { 
	              /** 
	               * aByte[i] & 0xF0 == 0xE0       ---> 判断当前字节是否以“1110”的形式开始； 
	               * aByte[i + 1] & 0xC0 == 0x80   ---> 判断下一个字节是否以“10”的形式开始； 
	               * aByte[i + 2] & 0xC0 == 0x80   ---> 判断下下一个字节是否以“10”的形式开始。 
	               * 假如条件都满足，则表示此断字节进行了utf-8编码，则将对其进行解码操作（即转 * 化为unicode编码） 
	               */ 
	              if ((aByte[i] & 0xF0) == 0xE0 && (aByte[i + 1] & 0xC0) == 0x80 && 
	                  (aByte[i + 2] & 0xC0) == 0x80) { 
	                  /** 
	                   * 将当前字节 1110 xxxx 转化为 xxxx 000000 000000 的形式，具体步骤为： 
	                   * 1110 xxxx << 12 = xxxx 000000 000000 
	                   * 1110 0100 << 12 = 0100 000000 000000 
	                   */ 
	                  sChar = (char) (aByte[i] << 12); 
	                  /** 
	                   * 将 前两个字节 转化为 xxxx xxxxxx 000000 的形式，具体步骤为： 
	                   * 10 xxxxxx & 0x003F = 0000 000000 xxxxxx 
	                   * 10 111000 & 0x003F = 0000 000000 111000 
	                   * 
	                   * 0000 000000 xxxxxx << 6 = 0000 xxxxxx 000000 
	                  * 0000 000000 111000 << 6 = 0000 111000 000000 
	                   * 
	                   * xxxx 000000 000000 | 0000 xxxxxx 000000 = xxxx xxxxxx 000000 
	                   * 0100 000000 000000 | 0000 111000 000000 = 0100 111000 000000 
	                   */ 
	                  sChar = (char) ((((aByte[i + 1] & 0x003F) << 6) | sChar)); 
	                  /** 
	                   * 将此三个字节转化为 xxxx xxxxxx xxxxxx 的形式，具体步骤为： 
	                   * 10 xxxxxx & 0x003F = 0000 0000 00 xxxxxx 
	                   * 10 101101 & 0x003F = 0000 0000 00 101101 
	                   * 
	                   * xxxx xxxxxx 000000 | 0000 000000 xxxxxx = xxxx xxxxxx xxxxxx 
	                   * 0100 111000 000000 | 0000 000000 101101 = 0100 111000 101101 
	                   */ 
	                  sChar = (char) ((aByte[i + 2] & 0x003F) | sChar); 
	                   i = i + 2; 
	                   sUnicodeStringBuffer.append(sChar); 
	               } else { 
	                   sUnicodeStringBuffer.append((char) aByte[i]); 
	               } 
	           } 
	         } 
	       return sUnicodeStringBuffer.toString(); 
	    } 
}


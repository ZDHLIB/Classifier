package FileOperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

public class ReadFromFile {

	/**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            ReadFromFile.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String readString = "";
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	readString = readString + "\r\n" + tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
 //       System.out.println(readString);
        return readString;
    }

    /**
     * 随机读取文件内容
     */
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
///////////////////////////
////  第二类读文件函数    ////
//////////////////////////
    static String resultString;
	static File file;
	static FileInputStream fis;
	static byte[] buf;
	
	
	//读取文件，参数为：文件名，以Stirng形式返回。
	public String readTxtFile(String fileName){
		file = new File(fileName);
		buf = null;
		
		if(!file.exists()){
			System.out.println("The file is not exist!");
			return null;
		}
		if(!file.isFile()){
			System.out.println("The "+fileName+" is not a readable file!");
			return null;
		}
		
		try{
			FileInputStream fis = new FileInputStream(file);
			buf = new byte[(int)file.length()];
			fis.read(buf);
			fis.close();
		}catch(Exception e){e.printStackTrace();}
		
		return new String(buf);
		
	}
	
	//读取文件，参数为：文件名\文本编码方式，以Stirng形式返回。
	public String readTxtFile(String fileName, String coding)
	{
		resultString = null;
		file=new File(fileName);
		fis = null;
		
		if(!file.exists()){
			System.out.println("The file is not exist!");
			return null;
		}
		if(!file.isFile()){
			System.out.println("The "+fileName+" is not a readable file!");
			return null;
		}
		
		try{
				fis = new FileInputStream(file);
				byte[] b=new byte[(int)file.length()];
				fis.read(b);
				fis.close();
				//字节数组转换成字符串
				resultString = ByteString.toSting(b,coding);//调用ByteString类的toString方法
		}catch (Exception e) {
				e.printStackTrace();
		}
		return resultString;
	}
	
	
	//===============================================================

	//读取文件，参数为：文件名\文本编码方式，以Stirng形式返回。
	public String readBinaryFile(String fileName, String coding)
	{
		resultString = null;
		file=new File(fileName);
		fis = null;
		
		if(!file.exists()){
			System.out.println("The file is not exist!");
			return null;
		}
		if(!file.isFile()){
			System.out.println("The "+fileName+" is not a readable file!");
			return null;
		}
		
		try{
				fis = new FileInputStream(file);
				buf=new byte[(int)file.length()];
				fis.read(buf);
				fis.close();
				//字节数组转换成字符串
				resultString = ByteString.toSting(buf,coding);//调用ByteString类的toString方法
		
				
				//返回加密后的结果
			//	resultString = MD5andKL.JIEMI(resultString);
				
		}catch (Exception e) {
				e.printStackTrace();
		}
		return resultString;
	}

    public static void main(String[] args) {
        String fileName = "E:\\JAVA\\ProgramFiles\\ZZ_FileSeperate_TestFiles\\SourceFiles\\Sample2\\C29-Transport\\C29-Transport070.txt";
        ReadFromFile a = new ReadFromFile();
     //   ReadFromFile.readFileByBytes(fileName);
     //   ReadFromFile.readFileByChars(fileName);
          a.readFileByLines(fileName);
     //   ReadFromFile.readFileByRandomAccess(fileName);
    }
}
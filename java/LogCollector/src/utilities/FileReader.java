package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class FileReader {
	private static Logger logger = Logger.getLogger(FileReader.class);
	
	public static ArrayList<String> getfiles(String path) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			String encoding = "UTF-8";
			File file = new File(path);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					result.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;
	}

	public static boolean isStatExists(String filepath) {
		File file = new File(filepath);
		if (file.isFile() && file.exists()) {
			return true;
		}
		return false;
	}

	public static String getTime(String line){
		String[] tmp = line.split(",");
		String[] tmp1 = tmp[0].split("@");
		String currenttime = tmp1[0];
		return currenttime;
	}
	
	public static ArrayList<String> asyncfile(String path, Calendar lasttime) {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat totaldf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(path, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line = "";
			rf.seek(nextend);
			int c = -1;
			int rows = 0;
			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					if(line != null){
						String currenttime = getTime(line);
						try {
							Calendar currentreadtime = Calendar.getInstance();
							currentreadtime.setTime(totaldf.parse(currenttime));
		//					logger.info("currenttime" + currentreadtime.getTime().toString());
		//					logger.info("latesttime" + latestreadtime.getTime().toString());
							if(currentreadtime.equals(lasttime) || currentreadtime.before(lasttime))
								break;
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						result.add(line);
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行  
                    line = rf.readLine();  
                    String currenttime = getTime(line);
					try {
						Calendar currentreadtime = Calendar.getInstance();
						currentreadtime.setTime(totaldf.parse(currenttime));
						if(currentreadtime.equals(lasttime))
							break;
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					logger.info(rows++ +" : " + line);
                }  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		logger.info("本次读取文件获得的最后时间 " + latestreadtime.getTime().toString());
		return result;
	}
}

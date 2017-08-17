package com.primeton.storm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GetData {

	public static void main(String[] args) {
		
		File logFile = new File("track.log");
		Random random = new Random();
		
		String[] hosts = {"www.taobao.com"};
		String[] session_id = {"ABASDFLHASUIHYIU12374823604623","ASFASGAS3456346SDGFSDF","67876DFHDFHDFHDFHDFHDFH","111111DFHDFHDFHDFHDFHDFH","22222DFHDFHDFHDFHDFHDFH"};
		
		String[] time ={"2014-01-07 08:40:50","2015-05-07 08:40:50","2016-06-07 09:40:50","2016-06-07 09:40:51","2016-06-07 09:40:52","2016-06-07 09:40:53","2016-06-07 09:40:54","2016-06-07 09:40:55"};
		
		StringBuffer buffer = new StringBuffer();
		
		for(int i = 0;i < 50; i++) {
			buffer.append(hosts[0] + "\t" + session_id[random.nextInt(5)] + "\t" + time[random.nextInt(8)] + "\n");
		}
		
		if(! logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		byte[] b = (buffer.toString()).getBytes();
		
		FileOutputStream fs ;
		
		try {
			fs = new FileOutputStream(logFile);
			fs.write(b);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

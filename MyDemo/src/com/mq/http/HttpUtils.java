package com.mq.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author mq
 *
 */
public class HttpUtils {

	private static final String URLPATH="http://192.168.1.101:8080/MyZhihu/";
	public HttpUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String getJsonContent(String url_path){
		String finalurl = URLPATH + url_path;
		try {
			URL url = new URL(finalurl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(3000);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			int code =connection.getResponseCode(); 
//			Log.d("code", ""+code);
			if(code == 200){
				InputStream in = connection.getInputStream();
				String result = changeInputStream(in);
				return result;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "not connection";
	}

	private static String changeInputStream(InputStream inputStream) {
		// TODO Auto-generated method stub
		String jsonString = " ";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		try {
			while((len = inputStream.read(data)) != -1){
				outputStream.write(data, 0, len);
			}
			jsonString = new String(outputStream.toByteArray());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonString;
	}
}

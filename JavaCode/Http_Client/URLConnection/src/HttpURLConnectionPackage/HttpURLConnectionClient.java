package HttpURLConnectionPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionClient {

	public static URL url=null;
	public static HttpURLConnection conn = null;
    public static OutputStream outputStream = null;
    public static InputStream inputStream = null;
    public static StringBuffer sbResult = new StringBuffer();
	
	public void HttpURLInit(String strurl){
		try {
			url = new URL(strurl);
			try {
				conn =(HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setURLConnectionProperty(){
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setReadTimeout(65 * 1000);
		conn.setConnectTimeout(65 * 1000);		
	}
	
	public void Connect(){
		try {
			conn.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void URLOutputStream(String strParameter){
		try {
			outputStream = conn.getOutputStream();
			outputStream.write(strParameter.getBytes("UTF-8"));
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String URLInputStream(){
		try {
			inputStream = conn.getInputStream();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			while((line = br.readLine()) != null){
				sbResult.append(line);
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sbResult.toString();		
	}
	
	public void disConnect(){
		try {
				if(outputStream != null){
					outputStream.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
				if(conn != null){
					conn.disconnect();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

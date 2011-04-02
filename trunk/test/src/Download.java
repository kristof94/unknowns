import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Download {
	public static void main(String[] args) {
		System.out.println(download("http://www.sgtjj.gov.cn/", new File("sgtjj.html")));
	}
	
	public static boolean download(String url, File file) {
		HttpURLConnection conn = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			conn = (HttpURLConnection)new URL(url).openConnection();
			conn.connect();
			if(conn.getResponseCode()==200) {
				in = new BufferedInputStream(conn.getInputStream());
				out = new BufferedOutputStream(new FileOutputStream(file));
				byte[] b = new byte[1024<<10];
				int i = 0;
				while((i = in.read(b))>-1) {
					if(i>0) out.write(b, 0, i);
				}
			} else {
				System.out.println(conn.getResponseCode()+":"+url);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in!=null) try {
				in.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			if(out!=null) try {
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			if(conn!=null) conn.disconnect();
		}
		return false;
	}
}

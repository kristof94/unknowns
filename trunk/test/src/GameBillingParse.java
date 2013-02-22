import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class GameBillingParse {
	public static void main(String[] args) {
//		System.out.println(decryptDES(new File("C:\\Documents and Settings\\Administrator\\桌面\\Charge_jzsh.xml")));
//		String[] result = parseApk(new File("C:\\Documents and Settings\\Administrator\\桌面\\g\\极速躲避.apk"));
//		for(String string:result) {
//			System.out.println(string);
//		}
		String dir = "g";
		if(args!=null && args.length>=1) dir = args[0];
		Map<String, String[]> map = scanDir(new File(dir));
		Iterator<Entry<String, String[]>> i = map.entrySet().iterator();
		while(i.hasNext()) {
			Entry<String, String[]> e = i.next();
			if(e.getValue()==null) {
				System.out.println(e.getKey()+"无法解析");
			} else {
				for(String v:e.getValue()) {
					System.out.println(e.getKey()+"|"+v);
				}
			}
		}
	}
	
	private static Map<String, String[]> scanDir(File dir) {
		Map<String, String[]> result = new LinkedHashMap<String, String[]>();
		File[] files = dir.listFiles();
		for(File file:files) {
			String name = file.getName();
			if(name.endsWith(".apk")) {
				result.put(name.substring(0, name.length()-4), parseApk(file));
			}
		}
		return result;
	}
	
	private static String[] parseApk(File apk) {
		try {
			ZipFile zipFile = new ZipFile(apk);
			String charge = getEntry(zipFile, "assets/Charge.xml");
//			System.out.println(decryptDES(charge));
			Document document = DocumentHelper.parseText(decryptDES(charge).replace("&", "&amp;"));
			Element resource = document.getRootElement();
			String smsPort = resource.elementText("USR-BUY-SMSCODE");
			String smsContent = resource.elementText("USR-BUY-SMS");
//			System.out.println(smsPort+":"+smsContent);
			String consumeCodeInfo = getEntry(zipFile, "assets/ConsumeCodeInfo.xml");
//			System.out.println(consumeCodeInfo);
			resource.clearContent();
			document.clearContent();
			document = DocumentHelper.parseText(consumeCodeInfo);
			Element consumerCodeList = document.getRootElement();
			List<Element> elements = consumerCodeList.elements("consumerCodeInfo");
			String[] result = new String[elements.size()];
			int i = 0;
			for(Element consumerCodeInfo:elements) {
				result[i++] = consumerCodeInfo.elementText("price")+"|"+smsPort+"|"+smsContent+" "+consumerCodeInfo.elementText("consumercode");
			}
			consumerCodeList.clearContent();
			document.clearContent();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getEntry(ZipFile zipFile, String entryPath) throws Exception {
		ZipEntry entry = zipFile.getEntry(entryPath);
		BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(entry));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int l;
		while((l=in.read(b))>-1) {
			out.write(b, 0, l);
		}
		in.close();
		out.close();
		entry.clone();
		return out.toString();
	}
	
	private static String decryptDES(File file) {
		InputStream fin = null;
		ByteArrayOutputStream baos = null;
		try {
			fin = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] data = new byte[512];
			int count = -1;
			while((count = fin.read(data)) > -1) {
				baos.write(data, 0, count);
			}
			return decryptDES(new String(baos.toByteArray()));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(fin != null) {
				try {
					fin.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				fin = null;
			}
			if(baos != null) {
				try {
					baos.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				baos = null;
			}
		}
		return null;
	}
	
	private static String decryptDES(String str) {
		try {
			int len = str.length() / 2;
			byte[] buf = new byte[len];
			for(int i = 0; i < len; i++) {
				buf[i] = Integer.valueOf(str.substring(2 * i, 2 * i + 2), 16).byteValue();
			}
			IvParameterSpec zeroIv = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
			SecretKeySpec key = new SecretKeySpec("lcmcLCMC".getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(2, key, zeroIv);
			byte[] decryptedData = cipher.doFinal(buf);
			return new String(decryptedData);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

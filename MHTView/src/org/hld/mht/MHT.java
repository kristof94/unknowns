package org.hld.mht;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class MHT {
	public static void main(String[] args) throws Exception {
//		long time = System.nanoTime();
//		String s = "<title>12=E5=B9=B4=E7=AD=89=E5=BE=85=EF=BC=8C=E7=BB=88=E6=88=90=E7=8E=B0=E5=";
//		String s = "77u/PCFET0NUWVBFIEhUTUwgUFVCTElDICItLy9XM0MvL0RURCBIVE1MIDQuMCBUcmFuc2l0aW9u\r\nYWwvL0VOIj4NCjxIVE1MPjxIRUFEPjxUSVRMRT4xMuW5tOetieW+he+8jOe7iOaIkOeOsOWunuKA\r\nlOKAlOawuOi/nOWcsO+8jFN0YW5kIEJ5IE1l77yM5a6M576O55qE5a6M57uT77yBIC0g5q+F5Yqb\r\n5bCx5piv6K+05Yiw5YGa5Yiw77yB5YWR546w5om/6K+655qE5Yqq5Yqb5bGLIC0g5q2q6YW35Y2a";
//		String s = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\r\nbWFnZVJlYWR5ccllPAAAADNQTFRF4GNj88PDzAEB5Hh40yQk1jEx+eDg7qio0RkZzQQEzgkJ3FBQ\r\nzxAQ6Y+P2UBAzAAA////2BZsTAAAAGlJREFUeNqEj9sKgEAIRN1LbRed8f+/thW2gog6DwqHUVT8\r\ngbwIdDbmaLCREJKKco0I88qFbEOAuSnRtYWYJ63Ju/CyaImE4SaFSBaQe6/z2EF30q7DhqiA+Gfi\r\nFAlVFVCdEDPy++0hwABOSA3gnXS3jgAAAABJRU5ErkJggg==";
//		String s = "FAlVFVCdEDPy++0hwABOSA3gnXS3jgAAAABJRU5ErkJggg==";
//		System.out.println("'"+decodeQ(s, "UTF-8")+"'");
//		System.out.println(new String(new BASE64Decoder().decodeBuffer(s)));
//		System.out.println(new String(decodeB(s.getBytes())));
//		System.out.println(System.nanoTime()-time);
//		System.out.println(new MHT("ie.mht"));
//		System.out.println(new MHT("unmht.mht"));
		new MHT("ie.mht").save();
		new MHT("unmht.mht").save();
//		System.out.println("dsf<base href=\"http://tancochan.ycool.com/\" />grw".replaceAll("<base\\s+href\\s*=\\s*\".*\".*((/>)|(</base>))", ""));
	}
	
	private static enum Transfer_Encoding {Null, Base64, QuotedPrintable};
	private static final byte[] NEWLINES = "\r\n".getBytes();
	private String boundary;
	private String content;
	private Map<String, Entity> entityMap = new HashMap<String, Entity>();
	private File file;
	private String lastReadTempString;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(file.getName());
		sb.append("\r\n");
		sb.append(content);
		Iterator<Entry<String, Entity>> iterator = entityMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next().getValue();
			sb.append("\r\n");
			sb.append("location: ");
			sb.append(entity.location);
			sb.append("\r\n");
			sb.append("type: ");
			sb.append(entity.type);
			sb.append("\r\n");
			sb.append("charset: ");
			sb.append(entity.charset);
			sb.append("\r\n");
			sb.append("transferEncoding: ");
			sb.append(entity.transferEncoding);
			sb.append("\r\n");
			sb.append("data: ");
			sb.append(entity.data);
			sb.append("\r\n");
//			if(entity.data!=null && entity.type!=null && entity.type.contains("text")) {
//				sb.append("content: ");
//				try {
//					sb.append(entity.charset==null?new String(entity.data):new String(entity.data, entity.charset));
//				} catch(UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				sb.append("\r\n");
//			}
			sb.append("--------------");
		}
		return sb.toString();
	}
	
	public MHT(String fileName) {
		file = new File(fileName);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			setBoundary(in);
			splitEntity(in, boundary);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in!=null) try {
				in.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setBoundary(BufferedReader in) throws Exception {
		String temp;
		while((temp=in.readLine())!=null) {
			int i = temp.indexOf("boundary");
			if(i>-1) {
				int start = temp.indexOf("\"", i);
				int end = 0;
				if(start>-1) {
					start++;
					end = temp.indexOf("\"", start);
				} else {
					start = temp.indexOf("=", i);
					start++;
					end = temp.length();
				}
				boundary = temp.substring(start, end);
				return;
			}
		}
	}
	
	public void splitEntity(BufferedReader in, String boundary) throws Exception {
		if(boundary==null) return;
		lastReadTempString = in.readLine();
		while(lastReadTempString != null) {
			if(lastReadTempString.startsWith(boundary, 2) && !lastReadTempString.endsWith("--")) {
				try {
					addEntity(in, boundary);
				} catch(Exception e) {
					e.printStackTrace();
					lastReadTempString = in.readLine();
				}
			} else {
				lastReadTempString = in.readLine();
			}
		}
	}
	
	public void addEntity(BufferedReader in, String boundary) throws Exception {
		Entity entity = new Entity();
		try {
			while((lastReadTempString = in.readLine())!=null) {
				if(entity.type==null && lastReadTempString.startsWith("Content-Type: ")) {
					if(lastReadTempString.contains("multipart")) {
						while((lastReadTempString=in.readLine())!=null) {
							if(lastReadTempString.length()==0) throw new Exception("unknown mht format");
							char c = lastReadTempString.charAt(0);
							if(c=='\t' || c==' ') {
								int i = lastReadTempString.indexOf("boundary");
								if(i>-1) {
									int start = lastReadTempString.indexOf("\"", i);
									int end = 0;
									if(start>-1) {
										start++;
										end = lastReadTempString.indexOf("\"", start);
									} else {
										start = lastReadTempString.indexOf("=", i)+1;
										end = lastReadTempString.length();
									}
									splitEntity(in, lastReadTempString.substring(start, end));
									return;
								}
							} else {
								throw new Exception("unknown mht format");
							}
						}
					}
					int i = lastReadTempString.indexOf(";");
					if(i==-1) {
						entity.type = lastReadTempString.substring(14);
						continue;
					} else {
						entity.type = lastReadTempString.substring(14, i);
						i = lastReadTempString.indexOf("charset", i);
						if(i>-1) {
							i = lastReadTempString.indexOf("\"", i)+1;
							entity.charset = lastReadTempString.substring(i, lastReadTempString.indexOf("\"", i));
							continue;
						}
						while((lastReadTempString=in.readLine())!=null) {
							if(lastReadTempString.length()==0) break;
							char c = lastReadTempString.charAt(0);
							if(c=='\t' || c==' ') {
								i = lastReadTempString.indexOf("charset");
								if(i>-1) {
									i = lastReadTempString.indexOf("\"", i)+1;
									entity.charset = lastReadTempString.substring(i, lastReadTempString.indexOf("\"", i));
									lastReadTempString=in.readLine();
									break;
								}
							} else {
								break;
							}
						}
					}
				}
				if(entity.transferEncoding==null && lastReadTempString.startsWith("Content-Transfer-Encoding: ")) {
					entity.transferEncoding = lastReadTempString.substring(27);
				} else if(entity.location==null && lastReadTempString.startsWith("Content-Location: ")) {
					entity.location = lastReadTempString.substring(18);
				} else if(lastReadTempString.length()==0) break;
			}
			if(entity.location!=null) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Transfer_Encoding transferEncoding;
				if("base64".equalsIgnoreCase(entity.transferEncoding)) transferEncoding = Transfer_Encoding.Base64;
				else if("quoted-printable".equalsIgnoreCase(entity.transferEncoding)) transferEncoding = Transfer_Encoding.QuotedPrintable;
				else transferEncoding = Transfer_Encoding.Null;
				while((lastReadTempString=in.readLine())!=null) {
					if(lastReadTempString.startsWith(boundary, 2)) break;
					switch(transferEncoding) {
					case Base64:
						out.write(decodeB(lastReadTempString.getBytes()));
						break;
					case QuotedPrintable:
						out.write(decodeQ(lastReadTempString.getBytes()));
						if(!lastReadTempString.endsWith("=")) out.write(NEWLINES);
						break;
					default:
						out.write(lastReadTempString.getBytes());
						out.write(NEWLINES);
						break;
					}
				}
				out.close();
				entity.data = out.toByteArray();
			}
		} finally {
			if(content==null && entity.type!=null && entity.type.contains("text")) {
				if(entity.charset!=null) content = new String(entity.data, entity.charset);
				else content = new String(entity.data);
			}
			if(entity.location!=null) entityMap.put(entity.location, entity);
		}
	}
	
	public String save() throws IOException {
		String fileName = file.getName();
		File parent = file.getParentFile();
		File dir = new File(parent, fileName+"_"+System.currentTimeMillis());
		dir.mkdirs();
		fileName = dir.getName()+"/";
		Map<String, String> map = new HashMap<String, String>();
		Iterator<Entry<String, Entity>> iterator = entityMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next().getValue();
			if(entity.data != null && !map.containsKey(entity.location)) {
				int i1 = entity.location.indexOf("?");
				int i2;
				if(i1>-1) i2 = entity.location.lastIndexOf("/", i1);
				else {
					i1 = entity.location.length();
					i2 = entity.location.lastIndexOf("/");
				}
				String entityName = (i2>-1?entity.location.substring(i2+1, i1):"");
				if(entityName.length()==0) entityName = System.currentTimeMillis()+"";
				entityName = entityName.toLowerCase();
				while(map.containsValue(entityName)) {
					entityName = System.nanoTime()+"_"+entityName;
				}
				map.put(entity.location, entityName);
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(dir, entityName)));
				out.write(entity.data);
				out.close();
			}
		}
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			content = content.replace(entry.getKey(), fileName+entry.getValue());
		}
		content = content.replaceAll("<base\\s+href\\s*=\\s*\".*\".*((/>)|(</base>))", "");
		File file = new File(parent, dir.getName()+".html");
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		out.write(content.getBytes());
		out.close();
		return file.getAbsolutePath();
	}
	
	private class Entity {
		private String type;
		private String charset;
		private String transferEncoding;
		private String location;
		private byte[] data;
	}
	
	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	public static byte[] decodeB(byte[] bytes) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int i = 0;
		int len = bytes.length;
		while(i<len) {
			char[] four = new char[4];
			int j = 0;
			while(j<4) {
				byte b = bytes[i++];
				if(b!='\r' || b!='\n') four[j++] = (char)b;
			}
			int k;
			if (four[3] == '=') {
				if (four[2] == '=') {
					k = 1;
				} else {
					k = 2;
				}
			} else {
				k = 3;
			}
			int aux = 0;
			for (j = 0; j < 4; j++) {
				if (four[j] != '=') {
					aux = aux | (chars.indexOf(four[j]) << (6 * (3 - j)));
				}
			}
			for (j = 0; j < k; j++) {
				out.write((aux >>> (8 * (2 - j))) & 0xFF);
			}
		}
		out.close();
		return out.toByteArray();
	}
	
	public static byte[] decodeQ(byte[] bytes) throws IOException {
		int len = bytes.length;
		int length = 0;
		for(int i = 0; i<len; i++) {
			byte b = bytes[i];
			if(b == '=') {
				i++;
				if(i==len) break;
				b = bytes[i];
				if(b == '\r' || b == '\n') {
					b = bytes[++i];
					if(b != '\n') {
						i--;
					}
					continue;
				}
				int result = -Character.digit(b, 16);
				result *= 16;
				result -= Character.digit(bytes[++i], 16);
				bytes[length++] = (byte)-result;
			} else {
				bytes[length++] = b;
			}
		}
		byte[] result = new byte[length];
		System.arraycopy(bytes, 0, result, 0, length);
//		System.out.println(new String(bytes, 0, length));
		return result;
	}
	
	public static String decodeQ(String str, String charsetName) throws IOException {
		byte[] bs = str.getBytes();
		int len = bs.length;
		int length = 0;
		for(int i = 0; i<len; i++) {
			byte b = bs[i];
			if(b == '=') {
				i++;
				if(i==len) break;
				b = bs[i];
				if(b == '\r' || b == '\n') {
					b = bs[++i];
					if(b != '\n') {
						i--;
					}
					continue;
				}
				int result = -Character.digit(b, 16);
				result *= 16;
				result -= Character.digit(bs[++i], 16);
				bs[length++] = (byte)-result;
			} else {
				bs[length++] = b;
			}
		}
		return new String(bs, 0, length, charsetName);
	}
}

package org.hld.mht;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class MHT {
	private static enum Transfer_Encoding {Null, Base64, QuotedPrintable};
	private static final byte[] NEWLINES = System.getProperty("line.separator", "\r\n").getBytes();
	private boolean isDecode;
	private String boundary;
	private String content;
	private String url;
	private Map<String, Entity> entityMap = new HashMap<String, Entity>();
	private File file;
	private String lastReadTempString;
	private String md5;
	
	public MHT(String fileName) {
		this(fileName, false);
	}
	
	public MHT(String fileName, boolean decode) {
		file = new File(fileName);
		if(decode) decode();
	}
	
	public boolean decode() {
		if(isDecode) return false;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			setBoundary(in);
			splitEntity(in, boundary);
			if(url!=null && content!=null)
				entityMap.remove(url);
			isDecode = true;
			return true;
		} catch(Exception e) {
			MiscUtil.err("decode mht error", e);
		} finally {
			if(in!=null) try {
				in.close();
			} catch(IOException e) {
				MiscUtil.err("close input stream error", e);
			}
		}
		return false;
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
					MiscUtil.err("add entity error", e);
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
				url = entity.location;
				if(entity.charset!=null) content = new String(entity.data, entity.charset);
				else content = new String(entity.data);
			}
			if(entity.location!=null && entity.data!=null && !entityMap.containsKey(entity.location)) entityMap.put(entity.location, entity);
		}
	}
	
	public String save(String dirPath) throws IOException {
		if(md5==null) {
			int length = (int)file.length();
			if(length==0) {
				md5 = "_0";
			} else {
				try {
					MessageDigest digest = MessageDigest.getInstance("MD5");
					StringBuilder sb = new StringBuilder("_");
					for(byte b:digest.digest((file.getPath()+":"+file.length()+":"+file.lastModified()).getBytes())) {
						sb.append(Integer.toHexString(0xFF & b));
					}
					md5 = sb.toString();
				} catch(NoSuchAlgorithmException e) {
					MiscUtil.err("generation MD5 digest error", e);
					md5 = "_e";
				}
			}
		}
		String fileName = file.getName();
		int i = fileName.lastIndexOf('.');
		if(i>0) fileName = fileName.substring(0, i);
		fileName += md5;
		if(!dirPath.equals(File.separatorChar)) dirPath+=File.separatorChar;
		File file = new File(dirPath+fileName+".html");
		if(file.isFile()) return file.getAbsolutePath();
		else if(file.exists()) {
			fileName = fileName + "_" + System.currentTimeMillis();
			file = new File(dirPath+fileName+".html");
		}
		if(!isDecode) decode();
		if(content==null) content = "";
		if(entityMap.size()>1) {
			dirPath = dirPath+fileName+File.separatorChar;
			File dir = new File(dirPath);
			dir.mkdirs();
			Iterator<Entry<String, Entity>> iterator = entityMap.entrySet().iterator();
			List<String> entityNameList = new ArrayList<String>();
			while(iterator.hasNext()) {
				Entity entity = iterator.next().getValue();
				int i1 = entity.location.indexOf("?");
				int i2;
				if(i1>-1) i2 = entity.location.lastIndexOf("/", i1);
				else {
					i1 = entity.location.length();
					i2 = entity.location.lastIndexOf("/");
				}
				String entityName = (i2>-1?entity.location.substring(i2+1, i1):"");
				if(entityName.length()==0) entityName = String.valueOf(System.currentTimeMillis());
				entityName = entityName.toLowerCase();
				while(entityNameList.contains(entityName)) {
					entityName = entityName+"_"+System.nanoTime();
				}
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(dir, entityName)));
				out.write(entity.data);
				out.close();
				content = content.replace(entity.location, dirPath+entityName);
			}
		}
		content = content.replaceAll("<base\\s+href\\s*=\\s*\".*\".*((/>)|(</base>))", "");
		if(url!=null) content = content.replace(url, file.getName());
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		out.write(content.getBytes());
		out.close();
		return file.getAbsolutePath();
	}
	
	public String save() throws IOException {
		return save(file.getParent());
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

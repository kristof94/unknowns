import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import android.content.res.AXmlResourceParser;


public class APK {
	private static String apkPath = "C:\\Documents and Settings\\Administrator\\桌面\\忘仙 1.7.42.apk";
	private static String smaliSrcPath = "C:\\Documents and Settings\\Administrator\\桌面\\HiAPN_1347005934312\\classes.dex.smali";
	private static String dex2jar = "E:\\android-sdk-windows\\dex2jar-0.0.9.7\\lib";
	private static String dex2jarMainClass = "com.googlecode.dex2jar.v3.Main";
	private static String smali = "E:\\android-sdk-windows\\Decompiler\\smali.jar";
	private static String baksmali = "E:\\android-sdk-windows\\Decompiler\\baksmali.jar";
	private static String dexdump = "E:\\android-sdk-windows\\platform-tools\\dexdump.exe";
	private static String AXMLPrinter2 = "E:\\android-sdk-windows\\AXMLPrinter2.jar";
	private static String testsign = "file:///"+"E:\\android-sdk-windows\\Signing APKs\\testsign.jar";
	private static String dex = "classes.dex";
	private static ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "jad -ff -space -nonlb -8 *.class");
	
	public static void main(String[] args) throws Exception {
		unpack();
//		pack();
//		parseApk();
	}
	
	private static void unpack() throws Exception {
		checkDirectory(extractApk());
	}
	
	private static void pack() throws Exception {
		File apk = new File(apkPath);
		File directory = new File(apk.getParent(), apk.getName().replace(".apk", "_") + System.currentTimeMillis());
		directory.mkdir();
		packageDex(directory);
		File bak = new File(directory, apk.getName());
		ZipInputStream in = new ZipInputStream(new FileInputStream(apk));
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(bak));
		byte[] b = new byte[102400];
		int len;
		ZipEntry entry;
		while((entry=in.getNextEntry())!=null) {
			if(dex.equals(entry.getName())) {
				out.putNextEntry(new ZipEntry(entry.getName()));
				FileInputStream read = new FileInputStream(directory+"/"+dex);
				while((len=read.read(b))!=-1) {
					if(len!=0) out.write(b, 0, len);
				}
				read.close();
			} else {
				out.putNextEntry(entry);
				while((len=in.read(b))!=-1) {
					if(len!=0) out.write(b, 0, len);
				}
				in.closeEntry();
			}
			out.closeEntry();
		}
		in.close();
		out.finish();
		out.close();
		signApk(bak);
		bak.renameTo(new File(directory.getPath()+".apk"));
		for(File f:directory.listFiles()) {
			f.delete();
		}
		directory.delete();
	}
	
	private static void parseApk() throws Exception {
		ZipFile zipFile = new ZipFile(apkPath);
		ZipEntry entry = zipFile.getEntry("AndroidManifest.xml");
		InputStream inputStream = zipFile.getInputStream(entry);
		AXmlResourceParser parser = new AXmlResourceParser();
		parser.open(inputStream);
		StringBuilder indent = new StringBuilder(10);
		while(true) {
			int type = parser.next();
			if(type == 1) {
				break;
			}
			switch(type) {
			case 0:
				log("<?xml version=\"1.0\" encoding=\"utf-8\"?>", new Object[0]);
				break;
			case 2:
				log("%s<%s%s", new Object[]{indent, getNamespacePrefix(parser.getPrefix()), parser.getName()});
				indent.append("\t");
				int namespaceCountBefore = parser.getNamespaceCount(parser.getDepth() - 1);
				int namespaceCount = parser.getNamespaceCount(parser.getDepth());
				for(int i = namespaceCountBefore; i != namespaceCount; i++) {
					log("%sxmlns:%s=\"%s\"", new Object[]{indent, parser.getNamespacePrefix(i), parser.getNamespaceUri(i)});
				}
				for(int i = 0; i != parser.getAttributeCount(); i++) {
					log("%s%s%s=\"%s\"", new Object[]{indent, getNamespacePrefix(parser.getAttributePrefix(i)), parser.getAttributeName(i),
							getAttributeValue(parser, i)});
				}
				log("%s>", new Object[]{indent});
				break;
			case 3:
				indent.setLength(indent.length() - "\t".length());
				log("%s</%s%s>", new Object[]{indent, getNamespacePrefix(parser.getPrefix()), parser.getName()});
				break;
			case 4:
				log("%s%s", new Object[]{indent, parser.getText()});
			case 1:
			}
		}
		zipFile.close();
	}
	
	private static final float[] RADIX_MULTS = {0.0039063F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F};
	private static final String[] DIMENSION_UNITS = {"px", "dip", "sp", "pt", "in", "mm", "", ""};
	private static final String[] FRACTION_UNITS = {"%", "%p", "", "", "", "", "", ""};
	
	private static String getNamespacePrefix(String prefix) {
		if((prefix == null) || (prefix.length() == 0)) {
			return "";
		}
		return prefix + ":";
	}
	
	private static String getAttributeValue(AXmlResourceParser parser, int index) {
		int type = parser.getAttributeValueType(index);
		int data = parser.getAttributeValueData(index);
		if(type == 3) {
			return parser.getAttributeValue(index);
		}
		if(type == 2) {
			return String.format("?%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)});
		}
		if(type == 1) {
			return String.format("@%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)});
		}
		if(type == 4) {
			return String.valueOf(Float.intBitsToFloat(data));
		}
		if(type == 17) {
			return String.format("0x%08X", new Object[]{Integer.valueOf(data)});
		}
		if(type == 18) {
			return data != 0?"true":"false";
		}
		if(type == 5) {
			return Float.toString(complexToFloat(data)) + DIMENSION_UNITS[(data & 0xF)];
		}
		if(type == 6) {
			return Float.toString(complexToFloat(data)) + FRACTION_UNITS[(data & 0xF)];
		}
		if((type >= 28) && (type <= 31)) {
			return String.format("#%08X", new Object[]{Integer.valueOf(data)});
		}
		if((type >= 16) && (type <= 31)) {
			return String.valueOf(data);
		}
		return String.format("<0x%X, type 0x%02X>", new Object[]{Integer.valueOf(data), Integer.valueOf(type)});
	}
	
	private static String getPackage(int id) {
		if(id >>> 24 == 1) {
			return "android:";
		}
		return "";
	}
	
	private static void log(String format, Object[] arguments) {
		System.out.printf(format, arguments);
		System.out.println();
	}
	
	public static float complexToFloat(int complex) {
		return (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4 & 0x3)];
	}
	
	private static File extractApk() throws Exception {
		File apk = new File(apkPath);
		File directory = new File(apk.getParent(), apk.getName().replace(".apk", "_") + System.currentTimeMillis());
		directory.mkdir();
		extractZip(apk, directory);
		return directory;
	}
	
	private static void packageDex(File directory) throws Exception {
		String cmd = "java -jar \""+smali+"\" \""+smaliSrcPath+"\" -o \""+dex+"\"";
		Process process = Runtime.getRuntime().exec(new String[]{"cmd","/c",cmd}, null, directory);
		InputStream read = process.getInputStream();
		byte[] b = new byte[1024000];
		if(read!=null) {
			while(read.read(b)!=-1) ;
			read.close();
		}
		process.destroy();
	}
	
	private static void signApk(File apk) throws Exception {
		URLClassLoader loader = URLClassLoader.newInstance(new URL[]{new URL(testsign)});
		Class clazz = loader.loadClass("testsign");
		Method method = clazz.getMethod("main", String[].class);
		method.setAccessible(true);
		method.invoke(null, (Object)new String[]{apk.getPath()});
	}

	private static void extractZip(File zip, File directory) throws ZipException, IOException, FileNotFoundException {
		ZipFile zipFile = new ZipFile(zip);
		Enumeration<ZipEntry> zipEntry = (Enumeration<ZipEntry>)zipFile.entries();
		while(zipEntry.hasMoreElements()) {
			ZipEntry entry = zipEntry.nextElement();
			File file = new File(directory, entry.getName());
			if(entry.isDirectory()) {
				file.mkdir();
			} else {
				File parent = file.getParentFile();
				if(!parent.exists()) parent.mkdirs();
				BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(entry));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				byte[] b = new byte[1024000];
				for(int i = in.read(b); i > -1; i = in.read(b)) {
					if(i != 0) out.write(b, 0, i);
				}
				in.close();
				out.close();
			}
		}
		zipFile.close();
	}
	
	private static void checkDirectory(File directory) throws Exception {
		for(File file:directory.listFiles()) {
			if(file.isDirectory()) {
				checkDirectory(file);
			} else {
				String name = file.getName();
				if(name.endsWith(".xml")) {
					decompileXml(file);
				} else if(name.endsWith(".dex")) {
					decompileDex(file);
				}
			}
		}
	}
	
	private static void decompileXml(File xml) {
		File directory = xml.getParentFile();
		File temp = new File(directory, xml.getName()+"."+System.currentTimeMillis()+".tmp");
		try {
			String cmd = "java -jar \""+AXMLPrinter2+"\" \""+xml.getPath()+"\" > \""+temp.getPath()+"\"";
			Process process = Runtime.getRuntime().exec(new String[]{"cmd","/c",cmd}, null, directory);
			InputStream read = process.getInputStream();
			byte[] b = new byte[1024000];
			if(read!=null) {
				while(read.read(b)!=-1) ;
				read.close();
			}
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(temp));
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(xml));
			for(int i = in.read(b); i > -1; i = in.read(b)) {
				if(i != 0) out.write(b, 0, i);
			}
			in.close();
			out.close();
			temp.delete();
			System.out.println("decompileXml:"+xml);
		} catch(Exception e) {
			System.err.println("decompileXml:"+xml+":"+e);
		}
	}
	
	private static void decompileDex(File dex) {
		try {
			String path = dex.getPath();
			File dir = new File(dex2jar);
			ArrayList<URL> urls = new ArrayList<URL>();
			for(File file:dir.listFiles()) {
				if(file.getName().endsWith(".jar")) urls.add(file.toURI().toURL());
			}
			URLClassLoader loader = URLClassLoader.newInstance(urls.toArray(new URL[urls.size()]));
			Class clazz = loader.loadClass(dex2jarMainClass);
			Method method = clazz.getMethod("doFile", File.class, File.class);
			File jar = new File(path+"_dex2jar.jar");
			method.invoke(null, dex, jar);
			File src = new File(path+".src");
			extractZip(jar, src);
			jar.delete();
			decompileClass(src);
			System.out.println("decompileDex:"+src);
			String cmd = "java -jar \""+baksmali+"\" \""+path+"\" -o \""+path+".smali\"";
			Process process = Runtime.getRuntime().exec(new String[]{"cmd","/c",cmd});
			InputStream read = process.getInputStream();
			byte[] b = new byte[1024000];
			if(read!=null) {
				while(read.read(b)!=-1) ;
				read.close();
			}
			process.destroy();
			System.out.println("decompileDex:"+path+".smali");
			cmd = "\""+dexdump+"\" -d \""+path+"\"";
			process = Runtime.getRuntime().exec(new String[]{cmd});
			BufferedInputStream in = new BufferedInputStream(process.getInputStream());
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path+".txt")));
			for(int i = in.read(b); i > -1; i = in.read(b)) {
				if(i != 0) out.write(b, 0, i);
			}
			in.close();
			out.close();
			System.out.println("decompileDex:"+path+".txt");
		} catch(Exception e) {
			System.err.println("decompileDex:"+dex+":"+e);
//			e.printStackTrace();
		}
	}
	
	private static void decompileClass(File directory) throws Exception {
		boolean needDecompile = false;
		for(File file:directory.listFiles()) {
			if(file.isDirectory()) {
				decompileClass(file);
			} else {
				needDecompile = true;
			}
		}
		if(needDecompile) {
			builder.directory(directory);
			builder.redirectErrorStream(true);
			Process process = builder.start();
			InputStream in = process.getInputStream();
			byte[] b = new byte[1024];
			while(in.read(b)!=-1) ;
			in.close();
			for(File file:directory.listFiles()) {
				String name = file.getName();
				if(name.endsWith(".jad")) {
					file.renameTo(new File(file.getParent(), file.getName().replace(".jad", ".java")));
				} else if(name.endsWith(".class")) {
					file.delete();
				}
			}
		}
	}
}

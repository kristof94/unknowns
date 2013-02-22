import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class EncodeTransition {
	public static void main(String[] args) throws Exception {
		gbk2utf8(new File("E:\\workspaces\\yzcg_v2\\src\\com\\yz"));
	}
	
	public static void gbk2utf8(File file) throws Exception {
		gbk2utf8(file, new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory() || pathname.getName().endsWith("java");
			}
		});
	}
	
	private static void gbk2utf8(File dir, FileFilter filter) throws Exception {
		for(File file:dir.listFiles(filter)) {
			if(file.isDirectory()) {
				gbk2utf8(file, filter);
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
				StringBuilder sb = new StringBuilder();
				char[] c = new char[8192];
				int l = -1;
				while((l=in.read(c))>-1) {
					if(l>0) {
						sb.append(c, 0, l);
					}
				}
				in.close();
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				out.write(sb.toString());
				out.close();
			}
		}
	}
}

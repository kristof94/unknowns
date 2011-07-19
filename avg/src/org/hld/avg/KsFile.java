package org.hld.avg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KsFile {
	
	public static void main(String[] args) {
		String s = "-.0";
		Pattern pattern = Pattern.compile("(true|false|[\\d\\.-]+)");
		System.out.println(pattern.matcher(s).matches());
	}
	
	private Map<String, Integer> labelMap = new LinkedHashMap<String, Integer>();
	
	public KsFile(File file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16"));
			String line;
			for(int i = 1; (line=in.readLine())!=null; i++) {
				line = line.trim();
				if(line.length()==0) continue;
				if(line.startsWith(";")) continue;
				if(line.startsWith("*")) {
					labelMap.put(line, i);
					continue;
				}
				System.out.println(line);
			}
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
}

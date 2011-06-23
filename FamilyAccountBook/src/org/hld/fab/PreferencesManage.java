package org.hld.fab;

import java.io.File;
import android.os.Environment;

public class PreferencesManage {
	private static final File DATA_DIR = new File(Environment.getExternalStorageDirectory(), ".fabData");
	
	public static final String[] MONTH = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	
	public static File getDataDir() {
		DATA_DIR.mkdir();
		return DATA_DIR;
	}
	
}

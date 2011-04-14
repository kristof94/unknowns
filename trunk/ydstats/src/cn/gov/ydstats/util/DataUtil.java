package cn.gov.ydstats.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import cn.gov.ydstats.bean.ArticleInfoBean;

/**
 * 保存文章数据
 * 
 * 数据的目录结构是：数据保存目录/主分类ID/子分类ID/文章ID
 * 
 * 数据的文件结构是：
 * 标题
 * 时间
 * 内容
 * 
 * 列表的目录结构是：数据保存目录/主分类ID/子分类ID/index.txt
 * 
 * 列表的文件结构是：
 * 本目录的文章总数
 * 文章1ID=文章1标题
 * 文章2ID=文章2标题
 */
public class DataUtil {
	private File dataDir;
	
	public DataUtil(File dataDir) {
		this.dataDir = dataDir;
	}
	
	public ArticleInfoBean getArticle(String id, String masterTypeId, String slaveTypeId) {
		ArticleInfoBean article = null;
		File data = new File(dataDir, (CommonUtil.isNotEmpty(masterTypeId)?masterTypeId+File.separator:"")+(CommonUtil.isNotEmpty(slaveTypeId)?slaveTypeId+File.separator:"")+id);
		System.out.println("getArticle:"+data.getAbsolutePath());
		if(data.isFile()) {
			article = new ArticleInfoBean();
			article.setId(id);
			if(CommonUtil.isNotEmpty(masterTypeId)) article.setMasterTypeId(masterTypeId);
			if(CommonUtil.isNotEmpty(slaveTypeId)) article.setSlaveTypeId(slaveTypeId);
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(data), "UTF-8"));
				article.setTitle(in.readLine());
				article.setDate(in.readLine());
				String temp;
				StringBuilder sb = new StringBuilder();
				while((temp=in.readLine())!=null) {
					sb.append(temp).append("\r\n");
				}
				article.setContent(sb.toString());
			} catch(Exception e) {
				e.printStackTrace();
				article = null;
			} finally {
				if(in!=null) try {
					in.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return article;
	}
	
	public void saveArticle(ArticleInfoBean article) {
		boolean flag = false;
		if(article.getId()==null) {
			flag = true;
			article.setId(System.currentTimeMillis()+""+System.nanoTime()+".txt");
		}
		File data = new File(dataDir, (CommonUtil.isNotEmpty(article.getMasterTypeId())?article.getMasterTypeId()+File.separator:"")+(CommonUtil.isNotEmpty(article.getSlaveTypeId())?article.getSlaveTypeId()+File.separator:"")+article.getId());
		System.out.println("saveArticle:"+data.getAbsolutePath());
		File dir = data.getParentFile();
		dir.mkdirs();
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(data), "UTF-8"));
			out.write(article.getTitle());
			out.write("\r\n");
			out.write(article.getDate());
			out.write("\r\n");
			out.write(article.getContent());
			out.close();
			if(flag) {
				File index = new File(dir, "index.txt");
				BufferedReader in = null;
				try {
					StringBuilder sb = new StringBuilder();
					int count = 0;
					if(index.isFile()) {
						in = new BufferedReader(new InputStreamReader(new FileInputStream(index), "UTF-8"));
						String temp = in.readLine();
						count = Integer.valueOf(temp);
						while((temp=in.readLine())!=null) {
							if(temp.length()>0) sb.append(temp).append("\r\n");
						}
					}
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(index), "UTF-8"));
					out.write(String.valueOf(count+1));
					out.write("\r\n");
					out.write(article.getId());
					out.write("=");
					out.write(article.getTitle());
					out.write("\r\n");
					out.write(sb.toString());
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					if(in!=null) try{in.close();}catch(IOException e){e.printStackTrace();}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(out!=null) try {
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteArticle(String id, String masterTypeId, String slaveTypeId) {
		File index = new File(dataDir, (CommonUtil.isNotEmpty(masterTypeId)?masterTypeId+File.separator:"")+(CommonUtil.isNotEmpty(slaveTypeId)?slaveTypeId+File.separator:"")+"index.txt");
		if(index.isFile()) {
			StringBuilder sb = new StringBuilder();
			BufferedReader in = null;
			BufferedWriter out = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(index), "UTF-8"));
				String s = in.readLine();
				int count = Integer.valueOf(s);
				boolean flag = false;
				while((s=in.readLine())!=null) {
					if(s.length()>0) {
						if(flag || !s.startsWith(id+"=")) {
							sb.append(s).append("\r\n");
						} else {
							flag = true;
							count--;
						}
					}
				}
				if(flag) {
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(index), "UTF-8"));
					out.write(String.valueOf(count));
					out.write("\r\n");
					out.write(sb.toString());
					File data = new File(index.getParentFile(), id);
					System.out.println("deleteArticle:"+data.getAbsolutePath());
					if(data.isFile()) data.delete();
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
			}
		}
	}
	
	public void deleteArticle(ArticleInfoBean article) {
		deleteArticle(article.getId(), article.getMasterTypeId(), article.getSlaveTypeId());
	}
	
	/**
	 * 获取文章列表
	 * @param masterTypeId
	 * @param slaveTypeId
	 * @return Map<文章ID, 文章标题>
	 */
	public Map<String, String> getList(String masterTypeId, String slaveTypeId) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		File index = new File(dataDir, (CommonUtil.isNotEmpty(masterTypeId)?masterTypeId+File.separator:"")+(CommonUtil.isNotEmpty(slaveTypeId)?slaveTypeId+File.separator:"")+"index.txt");
		System.out.println("getList:"+index.getAbsolutePath());
		if(index.isFile()) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(index), "UTF-8"));
				String temp;
				while((temp=in.readLine())!=null) {
					String[] s = temp.split("=", 2);
					if(s.length==2) map.put(s[0], s[1]);
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
		return map;
	}
}

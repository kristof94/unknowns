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
 * Y/N(是否在首页显示)
 * 内容
 * 
 * 列表的目录结构是：数据保存目录/主分类ID/子分类ID/index.txt
 * 
 * 列表的文件结构是：
 * 本目录的文章总数
 * 文章1ID=Y(在首页显示)=文章1标题
 * 文章2ID=N(不在首页显示)=文章2标题
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
				article.setIsIndexShow(in.readLine());
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
		boolean flag = true;
		if(article.getId()==null) {
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
			out.write(article.getIsIndexShow());
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
							if(temp.length()>0) {
								if(flag && temp.startsWith(article.getId()+"=")) {
									flag = false;
									sb.append(article.getId()).append("=").append(article.getIsIndexShow()).append("=").append(article.getTitle());
								} else {
									sb.append(temp);
								}
								sb.append("\r\n");
							}
						}
					}
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(index), "UTF-8"));
					if(flag) {
						out.write(String.valueOf(count+1));
						out.write("\r\n");
						out.write(article.getId());
						out.write("=");
						out.write(article.getIsIndexShow());
						out.write("=");
						out.write(article.getTitle());
					} else {
						out.write(String.valueOf(count));
					}
					out.write("\r\n");
					out.write(sb.toString());
					out.close();
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					if(in!=null) try{in.close();}catch(IOException e){e.printStackTrace();}
				}
				File all = new File(dataDir, article.getMasterTypeId()+File.separator+"all.txt");
				flag = true;
				try {
					StringBuilder sb = new StringBuilder();
					int count = 0;
					if(all.isFile()) {
						in = new BufferedReader(new InputStreamReader(new FileInputStream(all), "UTF-8"));
						String temp = in.readLine();
						count = Integer.valueOf(temp);
						while((temp=in.readLine())!=null) {
							if(temp.length()>0) {
								if(flag && temp.startsWith(article.getId()+"=")) {
									flag = false;
									sb.append(article.getId()).append("=").append(article.getIsIndexShow()).append("=").append(article.getSlaveTypeId()==null?"":article.getSlaveTypeId()).append("=").append(article.getTitle());
								} else {
									sb.append(temp);
								}
								sb.append("\r\n");
							}
						}
					}
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(all), "UTF-8"));
					if(flag) {
						out.write(String.valueOf(count+1));
						out.write("\r\n");
						out.write(article.getId());
						out.write("=");
						out.write(article.getIsIndexShow());
						out.write("=");
						out.write(article.getSlaveTypeId()==null?"":article.getSlaveTypeId());
						out.write("=");
						out.write(article.getTitle());
					} else {
						out.write(String.valueOf(count));
					}
					out.write("\r\n");
					out.write(sb.toString());
					out.close();
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
			try {
				File all = new File(dataDir, masterTypeId+File.separator+"all.txt");
				sb = new StringBuilder();
				in = new BufferedReader(new InputStreamReader(new FileInputStream(all), "UTF-8"));
				int count = Integer.valueOf(in.readLine());
				String s = null;
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
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(all), "UTF-8"));
					out.write(String.valueOf(count));
					out.write("\r\n");
					out.write(sb.toString());
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
	 * 获取文章总数
	 * @param masterTypeId
	 * @param slaveTypeId
	 * @return
	 */
	public int getCount(String masterTypeId, String slaveTypeId) {
		File index = new File(dataDir, (CommonUtil.isNotEmpty(masterTypeId)?masterTypeId+File.separator:"")+(CommonUtil.isNotEmpty(slaveTypeId)?slaveTypeId+File.separator:"")+"index.txt");
		System.out.println("getCount:"+index.getAbsolutePath());
		if(index.isFile()) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(index), "UTF-8"));
				return Integer.valueOf(in.readLine());
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
		return 0;
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
					String[] s = temp.split("=", 3);
					if(s.length==3) map.put(s[0], s[2]);
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
	
	/**
	 * 获取文章列表
	 * @param masterTypeId
	 * @param slaveTypeId
	 * @param isIndexShow 是否在首页显示
	 * @return Map<文章ID, 文章标题>
	 */
	public Map<String, String> getList(String masterTypeId, String slaveTypeId, boolean isIndexShow) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		File index = new File(dataDir, (CommonUtil.isNotEmpty(masterTypeId)?masterTypeId+File.separator:"")+(CommonUtil.isNotEmpty(slaveTypeId)?slaveTypeId+File.separator:"")+"index.txt");
		System.out.println("getList:"+index.getAbsolutePath());
		if(index.isFile()) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(index), "UTF-8"));
				String temp;
				while((temp=in.readLine())!=null) {
					String[] s = temp.split("=", 3);
					if(s.length==3 && ("Y".equals(s[1])==isIndexShow)) map.put(s[0], s[2]);
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
	
	/**
	 * 获取文章列表
	 * @param masterTypeId
	 * @param isIndexShow 是否在首页显示
	 * @return Map<文章ID, String{文章分类,文章标题}>
	 */
	public Map<String, String[]> getList(String masterTypeId, boolean isIndexShow) {
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		File all = new File(dataDir, (CommonUtil.isNotEmpty(masterTypeId)?masterTypeId+File.separator:"")+"all.txt");
		System.out.println("getList:"+all.getAbsolutePath());
		if(all.isFile()) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(all), "UTF-8"));
				String temp;
				while((temp=in.readLine())!=null) {
					String[] s = temp.split("=", 4);
					if(s.length==4 && ("Y".equals(s[1])==isIndexShow)) map.put(s[0], new String[]{CommonUtil.isNotEmpty(s[2])?s[2]:masterTypeId, s[3]});
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
	
	/**
	 * 获取文章列表
	 * @param masterTypeId
	 * @return Map<文章ID, String{文章分类,文章标题}>
	 */
	public Map<String, String[]> getList(String masterTypeId) {
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		File all = new File(dataDir, (CommonUtil.isNotEmpty(masterTypeId)?masterTypeId+File.separator:"")+"all.txt");
		System.out.println("getList:"+all.getAbsolutePath());
		if(all.isFile()) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(all), "UTF-8"));
				String temp;
				while((temp=in.readLine())!=null) {
					String[] s = temp.split("=", 4);
					if(s.length==4) map.put(s[0], new String[]{CommonUtil.isNotEmpty(s[2])?s[2]:masterTypeId, s[3]});
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

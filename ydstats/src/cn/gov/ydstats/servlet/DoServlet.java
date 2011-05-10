package cn.gov.ydstats.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.gov.ydstats.bean.ArticleInfoBean;
import cn.gov.ydstats.util.CommonUtil;
import cn.gov.ydstats.util.DataUtil;
import cn.gov.ydstats.util.TypeUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class DoServlet extends HttpServlet {
	private Configuration configuration;
	private DataUtil dataUtil;
	private File rootDir;
	private String staticPath;
	/**
	 * 根据类型获取文章列表
	 */
	private TemplateMethodModel getList = new TemplateMethodModel() {
		@Override
		public Object exec(List args) throws TemplateModelException {
			String masterTypeId = args.size()>0?(String)args.get(0):null;
			String slaveTypeId = args.size()>1?(String)args.get(1):null;
			String isIndexShow = args.size()>2?(String)args.get(2):null;
			Map<String, ?> map = null;
			if(CommonUtil.isNotEmpty(isIndexShow)) {
				if(CommonUtil.isNotEmpty(slaveTypeId)) map = dataUtil.getList(masterTypeId, slaveTypeId, "Y".equals(isIndexShow));
				else map = dataUtil.getList(masterTypeId, "Y".equals(isIndexShow));
			} else {
				if(CommonUtil.isNotEmpty(slaveTypeId)) map = dataUtil.getList(masterTypeId, slaveTypeId);
				else map = dataUtil.getList(masterTypeId);
			}
			int count = args.size()>3?Integer.valueOf((String)args.get(3)):map.size();
			int page = args.size()>4?Integer.valueOf((String)args.get(4)):1;
			if(count>=map.size() && page<2) return map;
			Map result = new LinkedHashMap();
			int first = count*(page-1);
			Iterator iterator = map.entrySet().iterator();
			int index = 0;
			while(index++<first && iterator.hasNext()) iterator.next();
			index = 0;
			while(index++<count && iterator.hasNext()) {
				Entry entry = (Entry)iterator.next();
				result.put(entry.getKey(), entry.getValue());
			}
			return result;
		}
	};
	
    public DoServlet() {
        super();
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setTimeFormat("HH:mm:SS");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:SS");
        configuration.setNumberFormat("#.########");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        configuration.setClassicCompatible(true);
        Map map = new HashMap();
        map.put("page", "/page.ftl");
        configuration.setAutoImports(map);
//        map.clear();
//        map.put("typeName", TYPE_NAME);
//        map.put("typeMap", TYPE_MAP);
//        try {
//			configuration.setAllSharedVariables(new SimpleHash(map));
//		} catch(TemplateModelException e) {
//			e.printStackTrace();
//		}
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	configuration.setServletContextForTemplateLoading(config.getServletContext(), "/");
    	rootDir = new File(config.getServletContext().getRealPath("/"));
    	dataUtil = new DataUtil(new File(rootDir, "data"));
    	staticPath = rootDir.getAbsolutePath()+File.separator+"static"+File.separator;
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doServlet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doServlet(request, response);
	}

	private void doServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if(servletPath.endsWith(".ftl")) {
			out(servletPath, request, response);
		} else if(servletPath.endsWith("glist.do")) {
			generateList(request, response);
		} else if(servletPath.endsWith("show.do")) {
			show(request, response);
		} else if(servletPath.endsWith("save.do")) {
			saveOrUpdate(request, response);
		} else if(servletPath.endsWith("del.do")) {
			delete(request, response);
		} else if(servletPath.endsWith("preview.do")) {
			previewInfo(request, response);
		} else if(servletPath.endsWith("ginfo.do")) {
			generateInfo(request, response);
		} else if(servletPath.endsWith("gindex.do")) {
			generateIndex(request, response);
		} else if(servletPath.endsWith("list.do")) {
			list(request, response);
		} else if(servletPath.endsWith("auto.do")) {
			autoGenerate(request, response);
		} else if(servletPath.endsWith("all.do")) {
			allGenerate(request, response);
		}
	}
	
	private void out(String page, Map map, Writer out) {
		try {
			Template template = configuration.getTemplate(page);
	        map.put("typeMap", TypeUtil.getTypeMap());
	        map.put("typeName", TypeUtil.getTypeName());
			template.process(map, out);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void out(String page, HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			Map map = new HashMap();
			HttpSession session = request.getSession(false);
			if(session!=null) {
				Enumeration<String> names = session.getAttributeNames();
				while(names.hasMoreElements()) {
					String name = names.nextElement();
					map.put(name, session.getAttribute(name));
				}
			}
			Enumeration<String> names = request.getAttributeNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				map.put(name, request.getAttribute(name));
			}
			out = response.getWriter();
			out(page, map, out);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(out!=null) out.close();
		}
	}
	
	/**
	 * 保存或更新文章
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	private void saveOrUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = CommonUtil.getParameter(req, "id");
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		ArticleInfoBean article = null;
		if(CommonUtil.isNotEmpty(id)) article = dataUtil.getArticle(id, masterTypeId, slaveTypeId);
		if(article==null) {
			article = new ArticleInfoBean();
			article.setMasterTypeId(masterTypeId);
			article.setSlaveTypeId(slaveTypeId);
			article.setDate(CommonUtil.formatDate(new Date()));
		}
		article.setTitle(CommonUtil.getParameter(req, "title"));
		article.setIsIndexShow(CommonUtil.getParameter(req, "isIndexShow"));
		String date = CommonUtil.getParameter(req, "date");
		if(CommonUtil.isNotEmpty(date)) article.setDate(date);
		article.setContent(CommonUtil.getParameter(req, "content"));
		dataUtil.saveArticle(article);
		res.sendRedirect("auto.do?id="+article.getId()+"&masterTypeId="+(masterTypeId==null?"":masterTypeId)+"&slaveTypeId="+(slaveTypeId==null?"":slaveTypeId));
	}
	
	/**
	 * 显示文章列表
	 * @param req
	 * @param res
	 */
	private void list(HttpServletRequest req, HttpServletResponse res) {
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		req.setAttribute("map", dataUtil.getList(masterTypeId, slaveTypeId));
		if(CommonUtil.isNotEmpty(masterTypeId)) req.setAttribute("masterTypeId", masterTypeId);
		if(CommonUtil.isNotEmpty(slaveTypeId)) req.setAttribute("slaveTypeId", slaveTypeId);
		out("articlelist.ftl", req, res);
	}
	
	/**
	 * 查看文章
	 * @param req
	 * @param res
	 */
	private void show(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameter(req, "id");
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		ArticleInfoBean article = dataUtil.getArticle(id, masterTypeId, slaveTypeId);
		if(article==null) {
			req.setAttribute("sysmsg", "查看的文章不存在");
			list(req, res);
		} else {
			req.setAttribute("info", article);
			out("article.ftl", req, res);
		}
	}
	
	/**
	 * 删除文章
	 * @param req
	 * @param res
	 */
	private void delete(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameter(req, "id");
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		String type = null;
		if(CommonUtil.isNotEmpty(slaveTypeId)) type = slaveTypeId+"/";
		else if(CommonUtil.isNotEmpty(masterTypeId)) type = masterTypeId+"/";
		else type = "";
		dataUtil.deleteArticle(id, masterTypeId, slaveTypeId);
		File file = new File(staticPath+type+id.replace(".txt", ".htm"));
		if(file.isFile()) file.delete();
		autoGenerate(req, res);
	}
	
	/**
	 * 预览信息
	 * @param req
	 * @param res
	 */
	private void previewInfo(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameter(req, "id");
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		ArticleInfoBean article = null;
		if(CommonUtil.isNotEmpty(id)) article = dataUtil.getArticle(id, masterTypeId, slaveTypeId);
		if(article==null) {
			article = new ArticleInfoBean();
			if(CommonUtil.isNotEmpty(masterTypeId)) article.setMasterTypeId(masterTypeId);
			if(CommonUtil.isNotEmpty(slaveTypeId)) article.setSlaveTypeId(slaveTypeId);
			article.setDate(CommonUtil.formatDate(new Date()));
		}
		String title = CommonUtil.getParameter(req, "title");
		if(CommonUtil.isNotEmpty(title)) article.setTitle(title);
		String date = CommonUtil.getParameter(req, "date");
		if(CommonUtil.isNotEmpty(date)) article.setDate(date);
		String isIndexShow = CommonUtil.getParameter(req, "isIndexShow");
		if(CommonUtil.isNotEmpty(isIndexShow)) article.setIsIndexShow(isIndexShow);
		String content = CommonUtil.getParameter(req, "content");
		if(CommonUtil.isNotEmpty(content)) article.setContent(content);
		req.setAttribute("info", article);
		req.setAttribute("path", "static");
		out("template/info.ftl", req, res);
	}
	
	/**
	 * 生成文章信息的静态页面
	 * @param req
	 * @param res
	 * @deprecated
	 */
	private void generateInfo(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameter(req, "id");
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		String type = null;
		if(CommonUtil.isNotEmpty(slaveTypeId)) type = slaveTypeId+"/";
		else if(CommonUtil.isNotEmpty(masterTypeId)) type = masterTypeId+"/";
		else type = "";
		Map map = new HashMap();
		if(!CommonUtil.isNotEmpty(type)) map.put("path", ".");
		File dir = new File(staticPath+type);
		dir.mkdir();
		if(CommonUtil.isNotEmpty(id)) {
			ArticleInfoBean article = dataUtil.getArticle(id, masterTypeId, slaveTypeId);
			if(article==null) {
				req.setAttribute("sysmsg", "要生成静态页面的文章不存在");
			} else {
				map.put("info", article);
				Writer out = null;
				try {
					String htmlName = article.getId().replace(".txt", ".htm");
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, htmlName)), "UTF-8"));
					out("template/info.ftl", map, out);
					req.setAttribute("sysmsg", "成功生成静态页面：<a href=\"static/"+type+htmlName+"\" target=\"_blank\"/>"+article.getTitle()+"</a>");
				} catch(Exception e) {
					e.printStackTrace();
					req.setAttribute("sysmsg", "生成静态页面时出错："+e);
				} finally {
					if(out!=null) try {
						out.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else {
			Map<String, String> articleMap = dataUtil.getList(masterTypeId, slaveTypeId);
			Iterator<String> iterator = articleMap.keySet().iterator();
			StringBuilder sb = new StringBuilder();
			while(iterator.hasNext()) {
				ArticleInfoBean article = dataUtil.getArticle(iterator.next(), masterTypeId, slaveTypeId);
				if(article!=null) {
					map.put("info", article);
					Writer out = null;
					try {
						String htmlName = article.getId().replace(".txt", ".htm");
						out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, htmlName)), "UTF-8"));
						out("template/info.ftl", map, out);
						sb.append("<br/><a href=\"static/");
						sb.append(type);
						sb.append(htmlName);
						sb.append("\" target=\"_blank\"/>");
						sb.append(article.getTitle());
						sb.append("</a>");
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
			}
			if(sb.length()==0) req.setAttribute("sysmsg", "未生成任何静态页面");
			else req.setAttribute("sysmsg", "成功生成静态页面"+sb.toString());
		}
		list(req, res);
	}

	/**
	 * 生成首页的静态页面
	 * @param req
	 * @param res
	 * @deprecated
	 */
	private void generateIndex(HttpServletRequest req, HttpServletResponse res) {
		Writer out = null;
		Map map = new HashMap();
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(staticPath+"index.htm"), "UTF-8"));
			map.put("getList", getList);
			out("template/index.ftl", map, out);
			req.setAttribute("sysmsg", "成功生成主页的静态页面：<a href=\"static/\" target=\"_blank\"/>主页</a>");
		} catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("sysmsg", "生成主页的静态页面时出错："+e);
		} finally {
			if(out!=null) try {
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		list(req, res);
	}
	
	/**
	 * 生成文章列表的静态页面
	 * @param req
	 * @param res
	 * @deprecated
	 */
	private void generateList(HttpServletRequest req, HttpServletResponse res) {
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		if(CommonUtil.isNotEmpty(masterTypeId) && TypeUtil.containsName(masterTypeId)) {
			Map<String, Map> result = new LinkedHashMap<String, Map>();
			Map<String, String> articleMap = dataUtil.getList(masterTypeId, null);
			if(articleMap.size()>0) result.put(masterTypeId, articleMap);
			String[] slaveTypeId = TypeUtil.getSlaveType(masterTypeId);
			for(String sid:slaveTypeId) result.put(sid, dataUtil.getList(masterTypeId, sid));
			Writer out = null;
			try {
				File dir = new File(staticPath+masterTypeId);
				dir.mkdir();
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, "index.htm")), "UTF-8"));
				Map map = new HashMap();
				map.put("map", result);
				map.put("masterTypeId", masterTypeId);
				out("template/list.ftl", map, out);
				req.setAttribute("sysmsg", "成功生成文章列表的静态页面：<a href=\"static/"+masterTypeId+"/\" target=\"_blank\"/>"+TypeUtil.getName(masterTypeId)+"</a>");
			} catch(Exception e) {
				e.printStackTrace();
				req.setAttribute("sysmsg", "生成文章列表的静态页面时出错："+e);
			} finally {
				if(out!=null) try {
					out.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			req.setAttribute("sysmsg", "主分类无效");
		}
		list(req, res);
	}

	/**
	 * 自动生成文章相关的静态页面
	 * @param req
	 * @param res
	 */
	private void autoGenerate(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameter(req, "id");
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		Writer out = null;
		StringBuilder sysmsg = new StringBuilder();
		Map map = new HashMap();
		map.put("getList", getList);
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(staticPath+"index.htm"), "UTF-8"));
			out("template/index.ftl", map, out);
			sysmsg.append("成功生成主页的静态页面：<a href=\"static/index.htm\" target=\"_blank\"/>主页</a>");
		} catch(Exception e) {
			e.printStackTrace();
			sysmsg.append("生成主页的静态页面时出错："+e);
		} finally {
			if(out!=null) try {
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(CommonUtil.isNotEmpty(masterTypeId)) {
			sysmsg.append("<br/>");
			File dir = null;
			map.put("masterTypeId", masterTypeId);
			try {
				if(CommonUtil.isNotEmpty(slaveTypeId)) {
					dir = new File(staticPath+masterTypeId);
					dir.mkdir();
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, "index.htm")), "UTF-8"));
					out("template/list.ftl", map, out);
					sysmsg.append("成功生成文章列表的静态页面：<a href=\"static/"+dir.getName()+"/index.htm\" target=\"_blank\"/>"+TypeUtil.getName(dir.getName())+"</a>");
					sysmsg.append("<br/>");
					map.put("slaveTypeId", slaveTypeId);
					dir = new File(staticPath+slaveTypeId);
				} else {
					dir = new File(staticPath+masterTypeId);
				}
				dir.mkdir();
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, "index.htm")), "UTF-8"));
				out("template/list.ftl", map, out);
				sysmsg.append("成功生成文章列表的静态页面：<a href=\"static/"+dir.getName()+"/index.htm\" target=\"_blank\"/>"+TypeUtil.getName(dir.getName())+"</a>");
			} catch(Exception e) {
				e.printStackTrace();
				sysmsg.append("生成文章列表的静态页面时出错："+e);
			} finally {
				if(out!=null) try {
					out.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(CommonUtil.isNotEmpty(id)) {
				sysmsg.append("<br/>");
				ArticleInfoBean article = dataUtil.getArticle(id, masterTypeId, slaveTypeId);
				if(article==null) {
					sysmsg.append("要生成静态页面的文章不存在");
				} else {
					map.put("info", article);
					try {
						String htmlName = article.getId().replace(".txt", ".htm");
						out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, htmlName)), "UTF-8"));
						out("template/info.ftl", map, out);
						sysmsg.append("成功生成静态页面：<a href=\"static/"+dir.getName()+"/"+htmlName+"\" target=\"_blank\"/>"+article.getTitle()+"</a>");
					} catch(Exception e) {
						e.printStackTrace();
						sysmsg.append("生成静态页面时出错："+e);
					} finally {
						if(out!=null) try {
							out.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		req.setAttribute("sysmsg", sysmsg.toString());
		list(req, res);
	}
	
	/**
	 * 生成所有静态页面
	 * @param req
	 * @param res
	 */
	private void allGenerate(HttpServletRequest req, HttpServletResponse res) {
		Writer out = null;
		StringBuilder sysmsg = new StringBuilder();
		Map map = new HashMap();
		map.put("getList", getList);
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(staticPath+"index.htm"), "UTF-8"));
			out("template/index.ftl", map, out);
			sysmsg.append("成功生成主页的静态页面：<a href=\"static/index.htm\" target=\"_blank\"/>主页</a>");
		} catch(Exception e) {
			e.printStackTrace();
			sysmsg.append("生成主页的静态页面时出错："+e);
		} finally {
			if(out!=null) try {
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		Map<String, String[]> typeMap = TypeUtil.getTypeMap();
		Iterator<String> iterator = typeMap.keySet().iterator();
		while(iterator.hasNext()) {
			String masterTypeId = iterator.next();
			map.put("masterTypeId", masterTypeId);
			sysmsg.append("<br/>");
			File dir = new File(staticPath+masterTypeId);
			dir.mkdir();
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, "index.htm")), "UTF-8"));
				out("template/list.ftl", map, out);
				sysmsg.append("成功生成文章列表的静态页面：<a href=\"static/"+dir.getName()+"/index.htm\" target=\"_blank\"/>"+TypeUtil.getName(dir.getName())+"</a>");
			} catch(Exception e) {
				e.printStackTrace();
				sysmsg.append("生成文章列表的静态页面时出错："+e);
			} finally {
				if(out!=null) try {
					out.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			Iterator<String> i = dataUtil.getList(masterTypeId, null).keySet().iterator();
			while(i.hasNext()) {
				String id = i.next();
				ArticleInfoBean article = dataUtil.getArticle(id, masterTypeId, null);
				if(article!=null) {
					map.put("info", article);
					try {
						String htmlName = article.getId().replace(".txt", ".htm");
						out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, htmlName)), "UTF-8"));
						out("template/info.ftl", map, out);
					} catch(Exception e) {
						e.printStackTrace();
						sysmsg.append("<br/>");
						sysmsg.append("生成静态页面时出错："+e);
					} finally {
						if(out!=null) try {
							out.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					sysmsg.append("<br/>");
					sysmsg.append("要生成静态页面的文章不存在："+masterTypeId+File.separator+id);
				}
			}
			map.remove("info");
			for(String slaveTypeId:TypeUtil.getSlaveType(masterTypeId)) {
				sysmsg.append("<br/>");
				map.put("slaveTypeId", slaveTypeId);
				dir = new File(staticPath+slaveTypeId);
				dir.mkdir();
				try {
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, "index.htm")), "UTF-8"));
					out("template/list.ftl", map, out);
					sysmsg.append("成功生成文章列表的静态页面：<a href=\"static/"+dir.getName()+"/index.htm\" target=\"_blank\"/>"+TypeUtil.getName(dir.getName())+"</a>");
				} catch(Exception e) {
					e.printStackTrace();
					sysmsg.append("生成文章列表的静态页面时出错："+e);
				} finally {
					if(out!=null) try {
						out.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
				map.remove("slaveTypeId");
				i = dataUtil.getList(masterTypeId, slaveTypeId).keySet().iterator();
				while(i.hasNext()) {
					String id = i.next();
					ArticleInfoBean article = dataUtil.getArticle(id, masterTypeId, slaveTypeId);
					if(article!=null) {
						map.put("info", article);
						try {
							String htmlName = article.getId().replace(".txt", ".htm");
							out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, htmlName)), "UTF-8"));
							out("template/info.ftl", map, out);
						} catch(Exception e) {
							e.printStackTrace();
							sysmsg.append("<br/>");
							sysmsg.append("生成静态页面时出错："+e);
						} finally {
							if(out!=null) try {
								out.close();
							} catch(IOException e) {
								e.printStackTrace();
							}
						}
					} else {
						sysmsg.append("<br/>");
						sysmsg.append("要生成静态页面的文章不存在："+masterTypeId+File.separator+id);
					}
				}
				map.remove("info");
			}
		}
		req.setAttribute("sysmsg", sysmsg.toString());
		list(req, res);
	}
}

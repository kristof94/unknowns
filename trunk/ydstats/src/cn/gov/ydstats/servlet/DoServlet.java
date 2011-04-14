package cn.gov.ydstats.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
			Map<String, String> map = dataUtil.getList(masterTypeId, slaveTypeId);
			int count = args.size()>2?Integer.valueOf((String)args.get(2)):map.size();
			int page = args.size()>3?Integer.valueOf((String)args.get(3)):1;
			if(count<=map.size() && page<2) return map;
			Map<String, String> result = new LinkedHashMap<String, String>();
			int first = count*(page-1);
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			int index = 0;
			while(index++<first && iterator.hasNext()) iterator.next();
			index = 0;
			while(index++<count && iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
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
		String date = CommonUtil.getParameter(req, "date");
		if(CommonUtil.isNotEmpty(date)) article.setDate(date);
		article.setContent(CommonUtil.getParameter(req, "content"));
		dataUtil.saveArticle(article);
		res.sendRedirect("list.do?masterTypeId="+(masterTypeId==null?"":masterTypeId)+"&slaveTypeId="+(slaveTypeId==null?"":slaveTypeId));
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
		dataUtil.deleteArticle(CommonUtil.getParameter(req, "id"), CommonUtil.getParameter(req, "masterTypeId"), CommonUtil.getParameter(req, "slaveTypeId"));
		list(req, res);
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
	 */
	private void generateInfo(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameter(req, "id");
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		String type = null;
		if(CommonUtil.isNotEmpty(masterTypeId)) type = masterTypeId+"/";
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
					out = new FileWriter(new File(dir, htmlName));
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
						out = new FileWriter(new File(dir, htmlName));
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
	 */
	private void generateIndex(HttpServletRequest req, HttpServletResponse res) {
		Writer out = null;
		Map map = new HashMap();
		try {
			out = new FileWriter(staticPath+"index.htm");
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
				out = new FileWriter(new File(dir, "index.htm"));
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
}

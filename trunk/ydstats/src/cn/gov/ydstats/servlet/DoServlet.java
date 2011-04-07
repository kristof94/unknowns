package cn.gov.ydstats.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.gov.ydstats.bean.ArticleInfoBean;
import cn.gov.ydstats.dao.ArticleInfoDAO;
import cn.gov.ydstats.util.CommonUtil;
import cn.gov.ydstats.util.PropertiesUtil;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;

public class DoServlet extends HttpServlet {
	private static final HashMap<String, String> TYPE_NAME = new HashMap<String, String>();
	private static final LinkedHashMap<String, String[]> TYPE_MAP = new LinkedHashMap<String, String[]>();
	static {
		TYPE_NAME.put("tjxx", "统计信息");
		TYPE_NAME.put("tjdt", "统计动态");
		TYPE_NAME.put("tjfx", "统计分析");
		TYPE_NAME.put("tjgb", "统计公报");
		TYPE_NAME.put("zwgk", "政务公开");
		TYPE_MAP.put("tjxx", new String[]{"tjdt","tjfx","tjgb"});
	}
	private Configuration configuration;
	
    public DoServlet() {
        super();
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setTimeFormat("HH:mm:SS");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:SS");
        configuration.setNumberFormat("#.########");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        configuration.setClassicCompatible(true);
        Map map = new HashMap();
        map.put("page", "/page.ftl");
        configuration.setAutoImports(map);
        map.clear();
        map.put("typeName", TYPE_NAME);
        map.put("typeMap", TYPE_MAP);
        try {
			configuration.setAllSharedVariables(new SimpleHash(map));
		} catch(TemplateModelException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	configuration.setServletContextForTemplateLoading(config.getServletContext(), "/");
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
			out(servletPath, null, request, response);
		} else if(servletPath.endsWith("list.do")) {
			list(request, response);
		} else if(servletPath.endsWith("login.do")) {
			login(request, response);
		} else if(servletPath.endsWith("logout.do")) {
			logout(request, response);
		} else if(servletPath.endsWith("sou.do")) {
			saveOrUpdate(request, response);
		}
	}
	
	private void out(String page, Map map, HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			Template template = configuration.getTemplate(page);
			out = response.getWriter();
			if(map==null) map = new HashMap();
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
			template.process(map, out);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(out!=null) out.close();
		}
	}
	
	private void login(HttpServletRequest req, HttpServletResponse res) {
		String username = CommonUtil.getParameter(req, "username", "");
		String password = CommonUtil.getParameter(req, "password", "");
		if(username == null || password == null) {
			PrintWriter out = null;
			try {
				out = res.getWriter();
				out.print("1");
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				if(out != null) out.close();
			}
			return;
		}
		String encrypt_password = CommonUtil.md5(password);
		String validate_password = PropertiesUtil.get(username);
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		if(validate_password != null && validate_password.equals(encrypt_password)) {
			req.getSession().setAttribute("admin_login", username);
			out.print("0");
		} else {
			out.print("1");
		}
		out.close();
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().removeAttribute("admin_login");
	}
	
	private void saveOrUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = CommonUtil.getParameter(req, "id", 0);
		ArticleInfoBean article = null;
		if(id>0) article = ArticleInfoDAO.get(id);
		if(article==null) {
			article = new ArticleInfoBean();
			article.setCreateDate(new Date());
		}
		article.setTitle(CommonUtil.getParameter(req, "title"));
		String masterTypeId = CommonUtil.getParameter(req, "masterTypeId");
		article.setMasterTypeId(masterTypeId);
		if(masterTypeId!=null) article.setMasterTypeName(TYPE_NAME.get(masterTypeId));
		String slaveTypeId = CommonUtil.getParameter(req, "slaveTypeId");
		article.setSlaveTypeId(slaveTypeId);
		if(slaveTypeId!=null) article.setSlaveTypeName(TYPE_NAME.get("slaveTypeId"));
		String createDate = CommonUtil.getParameter(req, "createDate");
		if(createDate!=null) article.setCreateDate(CommonUtil.parseDates(createDate));
		article.setContent(CommonUtil.getParameter(req, "content"));
		if(article.getId()!=null) ArticleInfoDAO.update(article);
		else ArticleInfoDAO.save(article);
		System.out.println(article.getId()+":"+article.getContent());
		req.getRequestDispatcher(req.getContextPath()+"/").forward(req, res);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) {
		out("moban.htm", null, request, response);
	}
}

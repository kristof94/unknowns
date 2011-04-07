package cn.gov.ydstats.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import cn.gov.ydstats.bean.ArticleInfoBean;
import cn.gov.ydstats.util.CommonUtil;

public class ArticleInfoDAO {
	private static final String TABLE_NAME = "YD_STATS_ARTICLE_INFO";
	private static final Class BEAN_CLASS = ArticleInfoBean.class;
	private static List<Field> fieldList = new LinkedList<Field>();
	private static String fieldSql;
	private static String insertSql;
	private static String updateSql;
	private static String selectSql;
	static {
//mysql
//		CREATE TABLE `yd_stats_article_info` (
//		  `ID` int(11) NOT NULL auto_increment,
//		  `MASTER_TYPE_ID` varchar(255) default NULL,
//		  `SLAVE_TYPE_ID` varchar(255) default NULL,
//		  `MASTER_TYPE_NAME` varchar(255) default NULL,
//		  `SLAVE_TYPE_NAME` varchar(255) default NULL,
//		  `TITLE` varchar(255) default NULL,
//		  `CREATE_DATE` datetime default NULL,
//		  `CONTENT` text,
//		  PRIMARY KEY  (`ID`)
//		) ENGINE=MyISAM DEFAULT CHARSET=utf8;
		StringBuilder sb = new StringBuilder();
		Field[] fields = BEAN_CLASS.getDeclaredFields();
		int i = 0;
		while(i<fields.length) {
			Field field = fields[i++];
			if("id".equals(field.getName())) continue;
			int j = 0, k = 0;
			char[] name = field.getName().toCharArray();
			char[] newname = new char[name.length*2];
			while(j<name.length) {
				char c = name[j++];
				if(Character.isLowerCase(c)) {
					newname[k++] = Character.toUpperCase(c);
				} else {
					newname[k++] = '_';
					newname[k++] = c;
				}
			}
			String temp = new String(newname, 0, k);
			sb.append(temp);
			field.setAccessible(true);
			fieldList.add(field);
			if(i<fields.length) sb.append(",");
		}
		fieldSql = sb.toString();
		//组合基本的插入语句
		sb = new StringBuilder("insert into ");
		sb.append(TABLE_NAME);
		sb.append(" (");
		sb.append(fieldSql);
		sb.append(") values (");
		i = 0;
		while(i++<fieldList.size()) {
			sb.append("?");
			if(i<fieldList.size()) sb.append(",");
		}
		sb.append(")");
		insertSql = sb.toString();
		//组合基本的更新语句
		sb = new StringBuilder("update ");
		sb.append(TABLE_NAME);
		sb.append(" set ");
		sb.append(fieldSql.replace(",", "=?,"));
		sb.append("=? where ID=?");
		updateSql = sb.toString();
		//组合基本的查询语句
		sb = new StringBuilder("select ");
		sb.append(fieldSql);
		sb.append(",ID from ");
		sb.append(TABLE_NAME);
		selectSql = sb.toString();
		System.out.println(fieldList);
		System.out.println(fieldSql);
		System.out.println(insertSql);
		System.out.println(updateSql);
		System.out.println(selectSql);
	}
	
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\workspaces\\ydstats\\WebContent\\Tongjiyuebao\\200702.htm");
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\200702.html"), "UTF-8"));
		String s;
		int flag = 0;
		while((s=in.readLine())!=null) {
			if(s.contains("您现在得位置")) {
				in.readLine();
				in.readLine();
				System.out.println(CommonUtil.getPlainText(in.readLine().trim()));
				in.readLine();
				in.readLine();
				in.readLine();
				System.out.println(CommonUtil.getPlainText(in.readLine().trim()));
				in.readLine();
				out.write("<table>");
				break;
			}
		}
		while((s=in.readLine())!=null) {
			if(s.length()>0) {
				if(s.contains("<table")) flag++;
				if(s.contains("</table>")) {
					if(flag==0) {
						out.write("</table>");
						break;
					}
					flag--;
				}
				out.write(s);
				out.newLine();
			}
		}
		in.close();
		out.close();
	}
	
	public static void save(ArticleInfoBean articleInfo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DAOUtil.getConnection();
			ps = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
			int i = 1;
			for(Field field:fieldList) {
				ps.setObject(i++, field.get(articleInfo));
			}
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while(rs.next()) {
				articleInfo.setId(rs.getInt(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtil.release(rs, ps, conn);
		}
	}

	public static void update(ArticleInfoBean articleInfo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DAOUtil.getConnection();
			ps = conn.prepareStatement(updateSql);
			int i = 0;
			for(Field field:fieldList) {
				ps.setObject(i++, field.get(articleInfo));
			}
			ps.setObject(i, articleInfo.getId());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtil.release(null, ps, conn);
		}
	}
	
	public static void delete(String... id) {
		if(id==null || id.length==0) return;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(TABLE_NAME);
		sql.append(" where ID in (");
		for(int i = 0; i<id.length; i++) {
			if(i>0) sql.append(",");
			sql.append(id[i]);
		}
		sql.append(")");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DAOUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtil.release(null, ps, conn);
		}
	}
	
	public static List<ArticleInfoBean> select() {
		List<ArticleInfoBean> list = new LinkedList<ArticleInfoBean>();
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			conn = DAOUtil.getConnection();
			s = conn.createStatement();
			rs = s.executeQuery(selectSql+" order by CREATE_DATE desc, ID desc");
			while(rs.next()) {
				ArticleInfoBean articleInfo = new ArticleInfoBean();
				int i = 0;
				for(Field field:fieldList) {
					field.set(articleInfo, rs.getObject(i++));
				}
				articleInfo.setId(rs.getInt(i));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtil.release(rs, s, conn);
		}
		return list;
	}
	
	public static ArticleInfoBean get(Integer id) {
		ArticleInfoBean articleInfo = null;
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			conn = DAOUtil.getConnection();
			s = conn.createStatement();
			rs = s.executeQuery(selectSql+" where ID="+id);
			while(rs.next()) {
				articleInfo = new ArticleInfoBean();
				int i = 0;
				for(Field field:fieldList) {
					field.set(articleInfo, rs.getObject(i++));
				}
				articleInfo.setId(rs.getInt(i));
				return articleInfo;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtil.release(rs, s, conn);
		}
		return articleInfo;
	}
}

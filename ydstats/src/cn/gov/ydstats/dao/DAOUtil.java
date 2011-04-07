package cn.gov.ydstats.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import cn.gov.ydstats.util.PropertiesUtil;

public class DAOUtil {
	private static final ConcurrentLinkedQueue<Connection> queue = new ConcurrentLinkedQueue<Connection>();
	
	public static Connection getConnection() throws Exception {
		Connection conn = queue.poll();
		if(conn!=null && !conn.isValid(1)) {
			System.err.println(conn+" is invalid");
			conn.close();
			conn = null;
		}
		if(conn == null) {
			Class.forName(PropertiesUtil.get("driver"));
			conn = DriverManager.getConnection(PropertiesUtil.get("url"), PropertiesUtil.get("username"), PropertiesUtil.get("password"));
		}
		return conn;
	}
	
	public static void release(ResultSet rs, Statement s, Connection conn) {
		release(rs, s);
		release(conn);
	}
	
	public static void release(ResultSet rs, Statement s) {
		if(rs != null) {
			try {
				rs.close();
			} catch(Exception e) {}
		}
		if(s != null) {
			try {
				s.close();
			} catch(Exception e) {}
		}
	}
	
	public static void release(Connection conn) {
		queue.add(conn);
	}
	
	public static void closeAll() {
		Iterator<Connection> iterator = queue.iterator();
		while(iterator.hasNext()) {
			try {
				iterator.next().close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

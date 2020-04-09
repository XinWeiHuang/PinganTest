package com.test.pingan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.mysql.jdbc.Driver;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private static Map<Integer, MyConnection> connections = new HashMap<Integer, MyConnection>();
	
	static {
		System.out.println("使用的JDBC:\t" + Driver.class);
		System.out.println("初始化连接池");
		try {
			Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			connections.put(connection.hashCode(), new MyConnection(connection));
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			connections.put(connection.hashCode(), new MyConnection(connection));
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			connections.put(connection.hashCode(), new MyConnection(connection));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("连接池初始化完成");
	}
	
	public static Connection getConnection() {
		while (true) {
			for (MyConnection myConnection : connections.values()) {
				if (myConnection.using()) {
					return myConnection.getConnection();
				}
			}
		}
	}
	
	public static void realease(Connection connection) {
		connections.get(connection.hashCode()).release();
	}
	
	private static class MyConnection {
		
		private Connection connection;
		private AtomicBoolean using = new AtomicBoolean(false);
		
		public MyConnection(Connection connection) {
			super();
			this.connection = connection;
		}

		public boolean using() {
			return using.compareAndSet(false, true);
		}
		
		public void release() {
			this.using.set(false);
		}
		
		public Connection getConnection() {
			return connection;
		}
	}
}

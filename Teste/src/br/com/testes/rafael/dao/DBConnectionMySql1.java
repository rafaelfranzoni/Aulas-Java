package br.com.testes.rafael.dao;


import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DBConnectionMySql1 {

	private static String URL_MYSQL = "jdbc:mysql://localhost:3306/relogio_ponto";
	private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
	private static final int MAX_TENTATIVAS = 3;
	private static String USER_MYSQL = "root";
	private static String PASS_MYSQL = "fabima0707";
	private static Connection connection;
	private static int tentativas;	

	public static Connection getConnection(){
		if (connection != null){ 
			return connection; 
		}		
		//System.out.println("Connect Local Database");
		
		try{
			Class.forName(DRIVER_CLASS_MYSQL);
			connection = DriverManager.getConnection(URL_MYSQL,USER_MYSQL,PASS_MYSQL);
			connection.setAutoCommit(true);
			tentativas = 0;
			return connection;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (Exception e) {
			tentativas++;
			if (tentativas >= MAX_TENTATIVAS){
				e.printStackTrace();
				System.exit(0);
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				return getConnection();
			}
		}
		return null;
	}
	
	public static void close(){
		if (connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}
		
	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs){
		try{
			if (conn != null){
				conn.close();
				System.out.println("Quit local database");
			}
			
			if (stmt != null){
				stmt.close();
			}
			
			if (rs != null){
				rs.close();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}

}
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.apache.catalina.Context;

	public class SQLresultset{

		public DataSource dataSource;
		public Connection connection;
		public Statement statement;
		public ResultSet resultSet;

		public SQLresultset(DataSource ds) {
			this.connection = null;
			this.statement = null;
			this.resultSet = null;
			this.dataSource = ds;
		}
		
		public DataSource getDataSource() {
			return dataSource;
		}
		
		@Resource(name="jdbc/ayjml")
		 public void openConnection() {
			try {
				connection = dataSource.getConnection();
				statement = connection.createStatement();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void closeConnection() {
			try {
				 connection.close();
				 statement.close();
				// resultSet.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public ResultSet executeQuery(String query) throws SQLException{

			resultSet = statement.executeQuery(query);
				
	        return resultSet;
		}
		
		public int executeUpdate(String query) {
			int resultSet = 0;
			try {
				resultSet = statement.executeUpdate(query);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return resultSet;
		}


}

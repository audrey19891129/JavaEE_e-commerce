package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Employee {
	private int id;
	private String firstname, lastname, username, password, title;
	
	public Employee() {
		
	}
	
	public Employee(int id, String firstname, String lastname, String username, String password, String title) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.title = title;
	}
	
	public Employee(String username, String password, DataSource dataSource) {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		
		
		try {
			ResultSet resultSet = sql.executeQuery("SELECT * FROM ayjml.employee WHERE username='" + username + "' AND ayjml.employee.password='" + password + "';");
			while(resultSet.next()) {
				this.id = resultSet.getInt("id");
				this.firstname = resultSet.getString("firstname");
				this.lastname = resultSet.getString("lastname");
				this.username = resultSet.getString("username");
				this.password = resultSet.getString("password");
				this.title = resultSet.getString("title");
			}
			sql.closeConnection();
		}catch(SQLException e) {
			this.id = 0;
			this.firstname = "";
			this.lastname = "";
			this.username = "";
			this.password = "";
			this.title = "";
		}
		sql.closeConnection();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", title=" + title + "]";
	}
	
	
	
	
}

package main;

import java.util.Date;

public class Person {
	
//Variables
	private String username;
	private String password;
	private Boolean isAdmin;
	private int level = 00;
	private int thePersonID = 00;
	private Date thedate = null;
	
//Constructor Blank
	public Person() {
		this.username = "USA";
		this.password = "1776"; //Happy 4th to my fellow Americans
		this.isAdmin = false;
		this.thedate = java.util.Calendar.getInstance().getTime();
	}
	
//Constructor Full
	public Person(String username, String password, Boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.thePersonID = 100000000 + ((int)(Math.random() * 99999999));
		this.thedate = java.util.Calendar.getInstance().getTime();
	}
// Basic to string
	@Override
	public String toString() {
		return "Person [username=" + username + ", isAdmin=" + isAdmin + ", level=" + level + ", thedate=" + thedate + "]";
	}
// Basic Getters and Setters
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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getThePersonID() {
		return thePersonID;
	}

	public void setThePersonID(int thePersonID) {
		this.thePersonID = thePersonID;
	}

	public Date getThedate() {
		return thedate;
	}

	public void setThedate(Date thedate) {
		this.thedate = thedate;
	}
	
	
	
}

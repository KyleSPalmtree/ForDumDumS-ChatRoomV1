package main;

import java.util.Date;

public class Message {

	//Variables
	private Date thedate = null;
	private String theUsername = "";
	private String theMessageContent = "";
	
	//Constructor Blank
	public Message()
	{
		thedate = java.util.Calendar.getInstance().getTime();
		theMessageContent = "default";
		theUsername = "default";
	}
	
	//Constructor Full
	public Message(String aUsername, String aMessageContent)
	{
		thedate = java.util.Calendar.getInstance().getTime();
		theMessageContent = aMessageContent;
		theUsername = aUsername;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// Basic Getters and Setters
	public Date getThedate() {
		return thedate;
	}
	public void setThedate(Date thedate) {
		this.thedate = thedate;
	}
	public String getTheUsername() {
		return theUsername;
	}
	public void setTheUsername(String theUsername) {
		this.theUsername = theUsername;
	}
	public String getTheMessageContent() {
		return theMessageContent;
	}
	public void setTheMessageContent(String theMessageContent) {
		this.theMessageContent = theMessageContent;
	}
	
	
	
	
	
	
}

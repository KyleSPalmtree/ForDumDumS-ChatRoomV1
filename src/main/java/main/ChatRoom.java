package main;

import java.util.ArrayList;
import java.util.Date;

public class ChatRoom {

//Variables
	private ArrayList <Message> messages = new ArrayList<Message>();
	private String theChatRoomName = "";
	private int theChatRoomID = -1;
	private String theUsernameOfOwner = "";
	private Date thedate = null;
	
//Constructor Blank
	public ChatRoom()
	{
		theChatRoomName = "untitled";
		theChatRoomID = 00000;
		theUsernameOfOwner = "default";
		thedate = java.util.Calendar.getInstance().getTime();
	}
	
//Constructor Full
	public ChatRoom(String aChatRoomName, String aUsername)
	{
		theChatRoomName = aChatRoomName;
		theChatRoomID = 10000000 + ((int)(Math.random() * 9999999));
		theUsernameOfOwner = aUsername;
		thedate = java.util.Calendar.getInstance().getTime();
	}
	
//object Methods
	
	//adds new messages to list
	public void addMessages(String aUsername, String aMessageContent) 
	{
		//Make Message
		Message aNewMessage = new Message(aUsername,aMessageContent);
		
		//Add to list of Messages
		messages.add(aNewMessage);	
	}
	
	//get the most recent message
	public Message getNewestMessage() 
	{
		return messages.get((messages.size() - 1));	//list and array start at zero, so to get the last message we need to go one down.
	}
	
	//get unwanted messages by it's text
	public Message getMessageByText(String text) 
	{
			Message ans = new Message(); // just sending us this
			
			//goes thought
			
			for(int i = messages.size(); i > 0; i--) // Goes by every messages starting with the most recent
			//== is to check if to values are equal where as = is to set to values to be equal
			//i -- is to subtract 1 from the Variable i
			//i (in this case will go down with every passed message)
			{
				//for(var,run as long as,var changer)
				
				// Will activate when the message content matches the text entered
				if(messages.get(i - 1).getTheMessageContent().matches(text)) 
				//Must use the method .matches() (from array library) when it comes to String, '==' wont work.
				{ 
					ans = messages.get(i - 1); // return comment, works because i -1 is the index of the comment we are looking for.
				}
				
		  	}
			
			return ans;
	}
			
	//remove unwanted messages by it's index
	public void removeMessageByindex(int index) 
	{
		messages.remove(index);
	}
	
	//remove unwanted messages by it's text
	public void removeMessageByText(String text) 
	{
		//goes thought
		for(int i = messages.size(); i > 0; i--) // Goes by every messages starting with the most recent
		//== is to check if to values are equal where as = is to set to values to be equal
		//i -- is to subtract 1 from the Variable i
		//i (in this case will go down with every passed message)
		{
			// Will activate when the message content matches the text entered
			if(messages.get(i - 1).getTheMessageContent().matches(text)) 
			//Must use the method .matches() (from array library) when it comes to String, '==' wont work.
			{ 
				messages.remove(i - 1); // remove comment, works because i -1 is the index of the comment we are looking for.
			}
	  	}
	}
	
	
	
// Basic Getters and Setters
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	public String getTheChatRoomName() {
		return theChatRoomName;
	}
	public void setTheChatRoomName(String theChatRoomName) {
		this.theChatRoomName = theChatRoomName;
	}
	public int getTheChatRoomID() {
		return theChatRoomID;
	}
	public void setTheChatRoomID(int theChatRoomID) {
		this.theChatRoomID = theChatRoomID;
	}
	public String getTheUsernameOfOwner() {
		return theUsernameOfOwner;
	}
	public void setTheUsernameOfOwner(String theUsernameOfOwner) {
		this.theUsernameOfOwner = theUsernameOfOwner;
	}

	public Date getThedate() {
		return thedate;
	}

	public void setThedate(Date thedate) {
		this.thedate = thedate;
	}
	
	
	
	
	
	
	
	
	
}

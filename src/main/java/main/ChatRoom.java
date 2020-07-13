package main;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoom {

//Variables
	private ArrayList <Message> messages = new ArrayList<Message>();
	private String theChatRoomName = "";
	private int theChatRoomID = -1;
	private String theUsernameOfOwner = "";
	private Date thedate = null;
 	private Date lastupdate = null;
	
//Constructor Blank
	public ChatRoom()
	{
		theChatRoomName = "untitled";
		theChatRoomID = 00000;
		theUsernameOfOwner = "default";
		thedate = java.util.Calendar.getInstance().getTime();
		lastupdate = java.util.Calendar.getInstance().getTime();
	}
	
//Constructor Full
	public ChatRoom(String aChatRoomName, String aUsername)
	{
		theChatRoomName = aChatRoomName;
		theChatRoomID = 10000000 + ((int)(Math.random() * 9999999));
		theUsernameOfOwner = aUsername;
		thedate = java.util.Calendar.getInstance().getTime();
		lastupdate = java.util.Calendar.getInstance().getTime();
	}
	
//object Methods
	
	//adds new messages to list
	public void addMessages(String aUsername, String aMessageContent) 
	{
		//Make Message
		Message aNewMessage = new Message(aUsername,aMessageContent);
		
		//Add to list of Messages
		messages.add(aNewMessage);	
		lastupdate = java.util.Calendar.getInstance().getTime();
		
		
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
		lastupdate = java.util.Calendar.getInstance().getTime();
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
				lastupdate = java.util.Calendar.getInstance().getTime();
			}
	  	}
	}
//XML Saving and loading
	// encode to XML file
		public void encodeToXML(String fileName)
		{
			//lets try something real quick
			final String SERIALIZED_FILE_NAME = fileName;

			XMLEncoder encoder = null;
			try
			{
				encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
			} 
			catch (NullPointerException | FileNotFoundException fileNotFound)
			{
				System.err.println("ERROR: While Creating or Opening the File");
			}
			encoder.writeObject(this);
			encoder.close();
		}

		// decode from a XML file
		public static ChatRoom decodeFromXML(String fileName) //Static meaning call from the object or class to make the object or class.
		{

			final String SERIALIZED_FILE_NAME = fileName;
			XMLDecoder decoder = null;
			try
			{
				decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
			} 
			catch (NullPointerException | FileNotFoundException e)
			{
				System.out.println("ERROR: File not found");
			}
			ChatRoom aChatRoom = (ChatRoom) decoder.readObject();
			//System.out.println(aChatRoom);
			return aChatRoom;
		}
	
// Basic toString
	@Override
	public String toString() {
		return (theChatRoomName + "\n \t Owner: " + theUsernameOfOwner +" | " + messages.size() + " Messages | Last Update: " + lastupdate);
	}	// /n ,make a new line .... i think
		// /t is for tab
			
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

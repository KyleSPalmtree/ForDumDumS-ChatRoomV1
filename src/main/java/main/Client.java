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

public class Client {
	
//Variables
	private ChatRoom currentChatRoom=null;
	private Person loginPerson =null;
	private boolean loggedin = false;
	//private server mainServer;
	//private boolean internalTesting = true;
	private ArrayList <Person> localUsers = new ArrayList<Person>();
	private ArrayList <ChatRoom> localRooms = new ArrayList<ChatRoom>(); 
	private int ClientID = 00;
	private Date thedate = null;
	private int backupCounter = 0;
	private String LastBackedUP = null;
	
//Constructor Blank
	public Client()
	{
		
	}
//Constructor Full	
	public Client(int aClientID){
		this.ClientID = aClientID;
		this.thedate = java.util.Calendar.getInstance().getTime();
		this.localUsers.add(new Person());	
	}
//Constructor Full
	//	public Client(){
	//		this.ClientID = 100000000 + ((int)(Math.random() * 99999999));
	//		this.thedate = java.util.Calendar.getInstance().getTime();
	//	} // Ctrl 7 to mass comment out, save this code for later when a server is include to make this an online messager system
	
//object Methods
	 
	//Logins user in to client
	public boolean loginToClient(String aUserName, String aPassword) {
		//need to look at all local user to find if this match them.
		
		boolean ans = false;
		
		//goes thought
		for(int i = localUsers.size(); i > 0; i--) // Goes by every messages starting with the most recent
		//== is to check if to values are equal where as = is to set to values to be equal
		//i -- is to subtract 1 from the Variable i
		//i (in this case will go down with every passed message)
		{
			// Will activate when the username content matches the text entered
			if(localUsers.get(i - 1).getUsername().matches(aUserName)) 
			//Must use the method .matches() (from array library) when it comes to String, '==' wont work.
			{ 
				if(localUsers.get(i - 1).getPassword().matches(aPassword))
				{
					loginPerson = localUsers.get(i - 1);
					ans = true;
					loggedin = true;
					
					System.out.println("Client: You are Now logged in as " + loginPerson.getUsername());
				}	
			}
	  	}
		
		if(!ans) 
		{
			System.out.println("Client: UserName or PassWord was not found");
		}
		return ans;	
	}
	
	//Logs you out of your account
	public void logoutofClient() {
		if (loggedin) 
		{
			loginPerson = null;
			loggedin = false;
			currentChatRoom = null;
			System.out.println("Client: Logged out, Bye");	
		}else {
			System.out.println("Client: Not Logged in");
		}
	}
	
	//Allows user to create new chat room if they are an admin
	public ChatRoom createNewLocalChatRoom(String aChatRoomName) 
	{
		ChatRoom ans = null;
		if (loggedin) {
			if (loginPerson.getIsAdmin()) {
				//ChatRoom(String aChatRoomName, String aUsername)
				ChatRoom aChatroom = new ChatRoom(aChatRoomName,loginPerson.getUsername());
				currentChatRoom = aChatroom;
				localRooms.add(aChatroom);
				ans = aChatroom;
				this.saveTheclient(false); // save when chat room is added
			}else {
				System.out.println("Client: Must Be Admin to Make Chat Room");
			}
		}else{
			System.out.println("Client: Please Login first");
		}
		return ans;
	}
	
	//Allows user to remove a chat room they have made, or if they are admit
	public void removeYourLocalChatRoom(String aChatRoomName) 
	{
		
		boolean ans = false;
				
		if (loggedin) {
			if (loginPerson.getIsAdmin()) { // if admit
				
				for(int i = localRooms.size(); i > 0; i--) // Goes by every messages starting with the most recent
					//== is to check if to values are equal where as = is to set to values to be equal
					//i -- is to subtract 1 from the Variable i
					//i (in this case will go down with every passed message)
					{
						// Will activate when the chatrRoom name matches the text entered
						if(localRooms.get(i - 1).getTheChatRoomName().matches(aChatRoomName)) 
						//Must use the method .matches() (from array library) when it comes to String, '==' wont work.
						{ 
							//does not check user name match
							ans = true;
							localRooms.remove(i - 1);
							System.out.println("Client: The Chat room was removed");
							this.saveTheclient(false); // save when chat room is removed
						}
				  	}
				if (!ans)
				{
					System.out.println("Client: The Chat Room you are looking for was not found");
				}
			}else { // if not admit
				
				for(int i = localRooms.size(); i > 0; i--) // Goes by every messages starting with the most recent
					//== is to check if to values are equal where as = is to set to values to be equal
					//i -- is to subtract 1 from the Variable i
					//i (in this case will go down with every passed message)
					{
						// Will activate when the chatrRoom name matches the text entered
						if(localRooms.get(i - 1).getTheChatRoomName().matches(aChatRoomName)) 
						//Must use the method .matches() (from array library) when it comes to String, '==' wont work.
						{ 
							ans = true;
							if (loginPerson.getUsername().matches(localRooms.get(i - 1).getTheUsernameOfOwner())) //checks user name match
							{
								localRooms.remove(i - 1);
								System.out.println("Client: The Chat room was removed");
								this.saveTheclient(false); // save when chat room is removed
							}else {
								System.out.println("Client: You Can't remove a ChatRoom you did not make unless you are an admit");
							}
						}
				  	}
				if (!ans)
				{
					System.out.println("Client: The Chat Room you are looking for was not found");
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		}else {
			System.out.println("Client: Please Login first");
		}
	}

	//Allows user to Add message to chat room,
	public void addMessagetocurrChatRoom(String aMessageText)
	{
		if (loggedin) {
			currentChatRoom.addMessages(loginPerson.getUsername(), aMessageText);
			this.saveTheclient(false); // save when messages is added
		}else {
			System.out.println("Client: Please Login first");
		}
	}
	
	//Allows user to remove a message they have created
	public void deleteUserMessageinChatRoom(String aMessageText) 
	{
		if (loggedin) 
		{
			Message ans = currentChatRoom.getMessageByText(aMessageText);
			if((ans.getTheUsername().matches(loginPerson.getUsername())) || (loginPerson.getIsAdmin())) { //if their comment, or if they are admin
				
				currentChatRoom.removeMessageByText(aMessageText);; // not the best way of doing this but will work
				
				if(loginPerson.getIsAdmin()) {
					System.out.println("Client: You can only delete this message because you are an admit");
					this.saveTheclient(false); // save when messages is deleted
				}
			}else {
				System.out.println("Client: You can only delete a messages that is yours");
			}	
			
		}else {
			System.out.println("Client: Please Login first");
		}
	}

	//creating a new user. 
	public boolean newUserloggingIn(String username, String password,boolean isAdmin) { 
		//public Person(String username, String password, Boolean isAdmin) {
		//if let's check if someone is logged in
		
		//good, now lets make it so the username can be the same ... 
		boolean taken = false;
		
		//goes thought each name
		for(int i = localUsers.size(); i > 0; i--) // Goes by every user starting with the most recent
		//== is to check if to values are equal where as = is to set to values to be equal
		//i -- is to subtract 1 from the Variable i
		//i (in this case will go down with every passed message)
		{
			// Will activate when the user name content matches the text entered
			if(localUsers.get(i - 1).getUsername().matches(username)) 
			//Must use the method .matches() (from array library) when it comes to String, '==' wont work.
			{ 
				System.out.println("Client: the user name you have requested is already in taken");
				taken = true;
			}
	  	}
		if (!taken) {
			if(loggedin) 
			{
				if(loginPerson.getIsAdmin()) 
				{
					localUsers.add(new Person(username,password,isAdmin));// admit if made admit
					if(isAdmin) //lets let them know they can make an admit
					{
						System.out.println("Client: user was made and is admit");
					}else {
						System.out.println("Client: user was made and is not admit");
					}
				}else {
					localUsers.add(new Person(username,password,false));// not admit
					if(isAdmin) //lets let them know they can make an admit
					{
						System.out.println("Client: You do not have permisson to make an admit - user was made");
					}
				}
				
			}else { // if not logged in
				localUsers.add(new Person(username,password,false));// not admit
				if(isAdmin) //lets let them know they can make an admit
				{
					System.out.println("Client: You do not have permisson to make an admit - user was made");
				}
			}
			this.saveTheclient(true); // save when new user added, and backed up
		}
		
		
		return !taken;
	
	}
	
	//saving the client - not the best way to do this be the simples
	protected void saveTheclient(boolean createbackup) 
	{
		if(createbackup)
		{
			this.encodeToXML(("ClientID" + ClientID + ".xml")); //creates save
			
			
			//let not make to many back ups
			//if more than 2, it will overwrite the oldest
			//this wont work because the date is in the name so, lets put the date somewhere else
			
			if (backupCounter > 5) {
				System.out.println("Client: Rewriting oldest Backups now");
				backupCounter = 0;
			}
			
			backupCounter = (backupCounter + 1); // add one with each back up
			
			LastBackedUP = java.util.Calendar.getInstance().getTime().toString().replaceAll(" ", "");//remove all white space
			//let take it out for now and see if this work, but let print ot out to
			
			this.encodeToXML(("BACKUPNum"+ backupCounter +"ClientID" + ClientID +".xml")); //creates Backup save
			
			
			
			
			
			System.out.println("Client: All changes saved and backed up");
			
		}else { // to just save
			this.encodeToXML(("ClientID" + ClientID + ".xml")); //creates save,
			System.out.println("Client: All changes saved");
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
				System.out.println("Client: The Client was saved");
			} 
			catch (NullPointerException | FileNotFoundException fileNotFound)
			{
				System.err.println("ERROR: While Creating or Opening the File");
			}
			encoder.writeObject(this);
			encoder.close();
		}

		// decode from a XML file
		public static Client decodeFromXML(String fileName) //Static meaning call from the object or class to make the object or class.
		{

			final String SERIALIZED_FILE_NAME = fileName;
			XMLDecoder decoder = null;
			try
			{
				decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
				System.out.println("Client: The Client was Loaded");
			} 
			catch (NullPointerException | FileNotFoundException e)
			{
				System.err.println("ERROR: File not found");
			}
			Client TheClient = (Client) decoder.readObject();
			//System.out.println(aChatRoom);
			return TheClient;
		}
		
	
// Basic Getters and Setters
	public ChatRoom getCurrentChatRoom() {
		return currentChatRoom;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public void setCurrentChatRoom(ChatRoom currentChatRoom) {
		this.currentChatRoom = currentChatRoom;
	}

	public Person getLoginPerson() {
		return loginPerson;
	}

	public void setLoginPerson(Person loginPerson) {
		this.loginPerson = loginPerson;
	}

	public ArrayList<Person> getLocalUsers() {
		return localUsers;
	}

	public void setLocalUsers(ArrayList<Person> localUsers) {
		this.localUsers = localUsers;
	}

	public ArrayList<ChatRoom> getLocalRooms() {
		return localRooms;
	}

	public void setLocalRooms(ArrayList<ChatRoom> localRooms) {
		this.localRooms = localRooms;
	}

	public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientID) {
		ClientID = clientID;
	}

	public Date getThedate() {
		return thedate;
	}

	public void setThedate(Date thedate) {
		this.thedate = thedate;
	}
	
	
	
	
	
	
	
	
}

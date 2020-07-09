package main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Step3LoginandLoadup {

	@Test
	public void testChatRooms()
	{
		//Block 1 - First make the client - before the user uses the app
		//one last thing, we have to make the ID -- 
			int ClientID = 100000000 + ((int)(Math.random() * 99999999));
			
			// make an ID so that the file dont pile up while testing
			ClientID = 100000001;
			
			
			Client MainClient = new Client(ClientID);
			//we need to open the same each time or create it the frist time
			
			//ID should never change
			MainClient.encodeToXML(("ClientID" + ClientID + ".xml")); //creates first save, then loads it
			
			System.out.println("Client: The Client was made "+ ClientID);
			MainClient = Client.decodeFromXML(("ClientID" + (MainClient.getClientID()) + ".xml"));
			System.out.println("Client: The Client was Opened "+ ClientID);
			
			//lets try it
			
			//1.1.1 - Now we Have to add example Users
			
				//public Person(String username, String password, Boolean isAdmin)
			 	Person ExampleUserA = new Person("ExampleUserA","PasswordA",true);
			 	Person ExampleUserB = new Person("ExampleUserB","PasswordB",false);
			 	Person ExampleUserC = new Person("ExampleUserC","PasswordC",false);
			 	Person ExampleUserD = new Person("ExampleUserD","PasswordD",false);
			 	Person ExampleUserE = new Person("ExampleUserE","PasswordE",false);
			 	Person ExampleUserF = new Person("ExampleUserF","PasswordF",false);
			 	
			//1.1.2 - Now to add these users to the client
			 	
			 	MainClient.getLocalUsers().add(ExampleUserA);
			 	MainClient.getLocalUsers().add(ExampleUserB);
			 	MainClient.getLocalUsers().add(ExampleUserC);
			 	MainClient.getLocalUsers().add(ExampleUserD);
			 	MainClient.getLocalUsers().add(ExampleUserE);
			 	MainClient.getLocalUsers().add(ExampleUserF);
			 	
		 	//1.2.1 - Now let's create some example chat rooms to add to the client
			 	
			 	//ChatRoom(String aChatRoomName, String aUsername)
			 	ChatRoom TestChatRoomA = new ChatRoom("TestChatRoomA","ExampleUserA");
			 	ChatRoom TestChatRoomB = new ChatRoom("TestChatRoomB","ExampleUserB");
			 	ChatRoom TestChatRoomC = new ChatRoom("TestChatRoomC","ExampleUserC");
			 	
		 	//1.2.2 - Now let's add these chatrooms to the Client
			 	
			 	MainClient.getLocalRooms().add(TestChatRoomA);
			 	MainClient.getLocalRooms().add(TestChatRoomB);
			 	MainClient.getLocalRooms().add(TestChatRoomC);
			 	
	 	//Block 2 - When the user opens the App - Yayyyyyyyy
		 	//2.1.1 - User logging in to their account. 
			 	
			 	//User A is logging in first.
			 		MainClient.loginToClient("ExampleUserA", "PasswordA");
			 	//Lets test to make sure they logged in right.
			 		assertEquals(MainClient.isLoggedin(),true);
			 		assertEquals(MainClient.getLoginPerson().getUsername(),"ExampleUserA");
		 		//Lets try to log out now ...
			 		MainClient.logoutofClient();
			 		assertEquals(MainClient.isLoggedin(),false);
			 		
			 	//User B now
			 		MainClient.loginToClient("ExampleUserB", "PasswordB");
			 	//Lets test to make sure they logged in right.
			 		assertEquals(MainClient.isLoggedin(),true);
			 		assertEquals(MainClient.getLoginPerson().getUsername(),"ExampleUserB");
		 		//Lets try to log out now ...
			 		MainClient.logoutofClient();
			 		assertEquals(MainClient.isLoggedin(),false);
			 
			 //2.2.1 - See chat room, join, make, delete as admit - since a is an admit
			 	//User A is logging in again, 
			 		MainClient.loginToClient("ExampleUserA", "PasswordA");
			 	//Lets test to make sure they logged in right.
			 		assertEquals(MainClient.isLoggedin(),true);
			 		assertEquals(MainClient.getLoginPerson().getUsername(),"ExampleUserA");
			 		
			 	//See(Check) rooms
			 		assertEquals(MainClient.getLocalRooms().get(0).getTheChatRoomName(),"TestChatRoomA");
			 		assertEquals(MainClient.getLocalRooms().get(1).getTheChatRoomName(),"TestChatRoomB");
			 		assertEquals(MainClient.getLocalRooms().get(2).getTheChatRoomName(),"TestChatRoomC");
		 		
			 	//make room as admit
			 		MainClient.createNewLocalChatRoom("addedTestChatroom");
			 		//check if it was added
			 		assertEquals(MainClient.getLocalRooms().get(3).getTheChatRoomName(),"addedTestChatroom");
			 		assertEquals(MainClient.getCurrentChatRoom().getTheChatRoomName(),"addedTestChatroom");
		 		
		 		//delete rooms as admit
		 			//first the one we made
		 			MainClient.removeYourLocalChatRoom("addedTestChatroom"); 
		 			//now admit should be able to remove any so lets try that.
		 			MainClient.removeYourLocalChatRoom("TestChatRoomC"); 
		 			
		 			//lets check it was really removed
		 			assertEquals(MainClient.getLocalRooms().size(),2); // only to chat room left, lets check which
		 			assertEquals(MainClient.getLocalRooms().get(0).getTheChatRoomName(),"TestChatRoomA");
			 		assertEquals(MainClient.getLocalRooms().get(1).getTheChatRoomName(),"TestChatRoomB");
			 		
	 		//2.3.1 - join room,see messages, send message, delete messages as an admit
				 		
		 		//Lets join a room now
			 		MainClient.setCurrentChatRoom(TestChatRoomA);	
			 		//should be empty
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().size(),0);
			 	//Lets add a messages
			 		MainClient.addMessagetocurrChatRoom("Message A from - ExampleUserA to Chatroom -TestChatRoomA");
			 		MainClient.addMessagetocurrChatRoom("Message B from - ExampleUserA to Chatroom -TestChatRoomA");
			 		
		 		//Lets check the messages again
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().size(),2);
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().get(0).getTheMessageContent()
			 				,"Message A from - ExampleUserA to Chatroom -TestChatRoomA");
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().get(1).getTheMessageContent()
			 				,"Message B from - ExampleUserA to Chatroom -TestChatRoomA");
 		//2.4.1 - See chat room, join, make, delete as non admit	
			 //Let test a non-admit user now
		 		//Lets try to log out now ...
			 		MainClient.logoutofClient();
			 		assertEquals(MainClient.isLoggedin(),false);
			 		
			 	//User C now
			 		MainClient.loginToClient("ExampleUserC", "PasswordC");
			 	//Lets test to make sure they logged in right.
			 		assertEquals(MainClient.isLoggedin(),true);
			 		assertEquals(MainClient.getLoginPerson().getUsername(),"ExampleUserC");
		 		
			 	//Lets join a room now
			 		MainClient.setCurrentChatRoom(TestChatRoomA);	
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().size(),2);
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().get(0).getTheMessageContent()
			 				,"Message A from - ExampleUserA to Chatroom -TestChatRoomA");
			 	//Let Add a message as user c
			 		MainClient.addMessagetocurrChatRoom("Message A from - ExampleUserC to Chatroom -TestChatRoomA");
			 		
			 	//Lets check that message now ... 
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().size(),3);
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().get(2).getTheMessageContent()
			 				,"Message A from - ExampleUserC to Chatroom -TestChatRoomA");
			 	//deleteing
			 		MainClient.deleteUserMessageinChatRoom("Message A from - ExampleUserC to Chatroom -TestChatRoomA");
			 		//checking
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().size(),2);
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().get(0).getTheMessageContent()
			 				,"Message A from - ExampleUserA to Chatroom -TestChatRoomA");
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().get(1).getTheMessageContent()
			 				,"Message B from - ExampleUserA to Chatroom -TestChatRoomA");
			 		MainClient.deleteUserMessageinChatRoom("Message B from - ExampleUserA to Chatroom -TestChatRoomA");
	 			//let leave a messages behide as user C
			 		MainClient.addMessagetocurrChatRoom("Message B from - ExampleUserC to Chatroom -TestChatRoomA");
		 		//Logged out
			 		MainClient.logoutofClient();
			 		assertEquals(MainClient.isLoggedin(),false);
			 		
			 	//User A is logging in now
			 		MainClient.loginToClient("ExampleUserA", "PasswordA");
			 	//Lets test to make sure they logged in right.
			 		assertEquals(MainClient.isLoggedin(),true);
			 		assertEquals(MainClient.getLoginPerson().getUsername(),"ExampleUserA");
			 		MainClient.setCurrentChatRoom(TestChatRoomA);
			 		assertEquals(MainClient.getCurrentChatRoom().getMessages().size(),3); 
			 		MainClient.deleteUserMessageinChatRoom("Message B from - ExampleUserC to Chatroom -TestChatRoomA");
			 			
			 	//Lets try to log out now ...
			 		MainClient.logoutofClient();
			 		assertEquals(MainClient.isLoggedin(),false);
 		//2.5.1 - Make new user as no one, as user, as admit	 		
			 	//lets create a user from the app as no one meaning no one logged in
			 		MainClient.newUserloggingIn("NewTestUserA", "PassWord1", true); // should not make this user an admit
			 		
			 		assertEquals(MainClient.getLocalUsers().get(7).getUsername(),"NewTestUserA"); //lets check for sure
			 		assertEquals(MainClient.getLocalUsers().get(7).getIsAdmin(),false); //lets check for sure
			 	//lets log in
			 	//We are User B now
			 		MainClient.loginToClient("ExampleUserB", "PasswordB");
			 	//Lets test to make sure they logged in right.
			 		assertEquals(MainClient.isLoggedin(),true);
			 		assertEquals(MainClient.getLoginPerson().getUsername(),"ExampleUserB");
		 		
			 	//lets create a user from the app as a non admit meaning no one logged in
			 		MainClient.newUserloggingIn("NewTestUserB", "PassWord2", true); // should not make this user an admit
			 		assertEquals(MainClient.getLocalUsers().get(8).getUsername(),"NewTestUserB"); //lets check for sure
			 		assertEquals(MainClient.getLocalUsers().get(8).getIsAdmin(),false); //lets check for sure
			 	//Lets try to log out now ...
			 		MainClient.logoutofClient();
			 		assertEquals(MainClient.isLoggedin(),false);
			 		
			 	//we are user A now
			 		MainClient.loginToClient("ExampleUserA", "PasswordA");
			 	//Lets test to make sure they logged in right.
			 		assertEquals(MainClient.isLoggedin(),true);
			 		assertEquals(MainClient.getLoginPerson().getUsername(),"ExampleUserA");
			 		
			 	//lets create a user from the app as a non admit meaning no one logged in
			 		MainClient.newUserloggingIn("NewTestUserC", "PassWord3", true); // should make this user an admit
			 		assertEquals(MainClient.getLocalUsers().get(9).getUsername(),"NewTestUserC"); //lets check for sure
			 		assertEquals(MainClient.getLocalUsers().get(9).getIsAdmin(),true); //lets check for sure	
			 			
		 		//Lets try to log out now ...
			 		MainClient.logoutofClient();
			 		assertEquals(MainClient.isLoggedin(),false);
			 		MainClient.newUserloggingIn("NewTestUserD", "PassWord3", true); // should not make this user an admit
			 	//let see where it fail
			 		
			 	
	}
	
}

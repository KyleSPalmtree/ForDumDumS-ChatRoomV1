package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Step2ChatRoomSaveAndLoad {

	@Test
	public void testChatRooms()
	{
		//ChatRoom(String aChatRoomName, String aUsername)
		ChatRoom TestRoom1 = new ChatRoom("TestRoom1", "TestPersonA");
			//testing basics Creation
				assertEquals("TestRoom1",TestRoom1.getTheChatRoomName());
				assertEquals("TestPersonA",TestRoom1.getTheUsernameOfOwner());
				assertTrue(10000000 < TestRoom1.getTheChatRoomID());
				//System.out.println(TestRoom1.getTheChatRoomID());
				//System.out.println(TestRoom1.getThedate());
			//add messages
				//(String aUsername, String aMessageContent)
				TestRoom1.addMessages("TestPersonB","This is a test comment on ChatRoom-TestRoom1 by TestPersonB");
				TestRoom1.addMessages("TestPersonC","This is test comment A on ChatRoom-TestRoom1 by TestPersonC");
				TestRoom1.addMessages("TestPersonC","This is test comment B on ChatRoom-TestRoom1 by TestPersonC");
				TestRoom1.addMessages("TestPersonD","This is test comment A on ChatRoom-TestRoom1 by TestPersonD");
				TestRoom1.addMessages("TestPersonD","This is test comment B on ChatRoom-TestRoom1 by TestPersonD");
				TestRoom1.addMessages("TestPersonD","This is test comment C on ChatRoom-TestRoom1 by TestPersonD");
				TestRoom1.addMessages("TestPersonE","This is test comment A on ChatRoom-TestRoom1 by TestPersonE");
				TestRoom1.addMessages("TestPersonE","This is test comment B on ChatRoom-TestRoom1 by TestPersonE");
				TestRoom1.addMessages("TestPersonE","This is test comment C on ChatRoom-TestRoom1 by TestPersonE");
				TestRoom1.addMessages("TestPersonE","This is test comment D on ChatRoom-TestRoom1 by TestPersonE");
			//Saving Chat room
				TestRoom1.encodeToXML(TestRoom1.getTheChatRoomName()+"_TESTFILE.XML");
				ChatRoom TestRoom2= ChatRoom.decodeFromXML(TestRoom1.getTheChatRoomName()+"_TESTFILE.XML");
				assertEquals(TestRoom1.getTheUsernameOfOwner(),TestRoom2.getTheUsernameOfOwner());
				assertEquals(TestRoom1.getTheChatRoomName(),TestRoom2.getTheChatRoomName());
				assertEquals(TestRoom1.getMessages().get(3).getTheMessageContent(),
						TestRoom2.getMessages().get(3).getTheMessageContent());
				
	}
	
}

package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Step1ChatRoomAndMessages
{

	@Test
	public void testChatRooms()
	{
		//ChatRoom(String aChatRoomName, String aUsername)
		ChatRoom TestRoom1 = new ChatRoom("TestRoom1", "TestPersonA");
			//testing basics Creation
				assertEquals("TestRoom1",TestRoom1.getTheChatRoomName());
				assertEquals("TestPersonA",TestRoom1.getTheUsernameOfOwner());
				assertTrue(10000000 < TestRoom1.getTheChatRoomID());
				System.out.println(TestRoom1.getTheChatRoomID());
				System.out.println(TestRoom1.getThedate());
			// test adding messages
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
				//Check Messages.
					//comment 1
					System.out.println(TestRoom1.getMessages().get(0).getThedate());
					assertEquals("TestPersonB",TestRoom1.getMessages().get(0).getTheUsername());
					assertEquals("This is a test comment on ChatRoom-TestRoom1 by TestPersonB"
							,TestRoom1.getMessages().get(0).getTheMessageContent());
					
					//comment 2
					System.out.println(TestRoom1.getMessages().get(1).getThedate());
					assertEquals("TestPersonC",TestRoom1.getMessages().get(1).getTheUsername());
					assertEquals("This is test comment A on ChatRoom-TestRoom1 by TestPersonC"
							,TestRoom1.getMessages().get(1).getTheMessageContent());
					
					//comment 3
					System.out.println(TestRoom1.getMessages().get(3).getThedate());
					assertEquals("TestPersonD",TestRoom1.getMessages().get(3).getTheUsername());
					assertEquals("This is test comment A on ChatRoom-TestRoom1 by TestPersonD"
							,TestRoom1.getMessages().get(3).getTheMessageContent());
					
					//comment 4
					System.out.println(TestRoom1.getMessages().get(6).getThedate());
					assertEquals("TestPersonE",TestRoom1.getMessages().get(6).getTheUsername());
					assertEquals("This is test comment A on ChatRoom-TestRoom1 by TestPersonE"
							,TestRoom1.getMessages().get(6).getTheMessageContent());
					
					
	}
}
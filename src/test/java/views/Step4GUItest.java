package views;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import app.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ChatRoom;
import main.Client;
import main.Person;


@ExtendWith(ApplicationExtension.class)
public class Step4GUItest
{
	@Start
	public void start(Stage stage) throws Exception { 
		//Block 1 - First make the client - before the user uses the app // Setting up to log in
				int ClientID = 100000000 + ((int)(Math.random() * 99999999));
				
				// make an ID so that the file don't pile up while testing
				ClientID = 100000001;
				//Client DummyClient = new Client(); // creating a dummy client
				//Getting Login View and Controller
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("../views/LoginView.fxml")); 
					BorderPane LoginView = loader.load(); //Get view
					LoginViewController ControllerA = loader.getController(); //Get controller
					ControllerA.setStage(stage);//the stage
				//Loading up the main Scene
					Scene s = new Scene(LoginView);
					stage.setTitle("LOGIN"); 
					stage.setScene(s);
					stage.show();	
	}
	
	//robot Type In Text
		private void robotTypeInText(FxRobot robot, String text, String target)
		{
			robot.clickOn(target); //id of target
			robot.write(text); //Text to type in
		}

		//robot log in
		private void robotLogIn(FxRobot robot, String username, String password)
		{
			robotTypeInText(robot, username, "#LoginUserName"); // type in to user name slot
			robotTypeInText(robot, password, "#LoginPassWord"); // type in to Pass Word slot
			
			//these use the reg ID
			robot.clickOn("#LoginSubmit");// will click submit
		}	
		private void robotLogIOut(FxRobot robot)
		{
			//from chat romm
			robot.clickOn("#CRback");// will click submit
			
			//these use the reg ID
			robot.clickOn("#MainLogOut");// will click submit
		}
		
		//robot select Chat room item from the list
		private void robotselectChatRoomItem(FxRobot robot, int i)
		{
			ListView<ChatRoom> listofRoom = (ListView<ChatRoom>)robot.lookup("#MainChatList").queryAs(ListView.class);
			listofRoom.getSelectionModel().clearAndSelect(i);
			robot.clickOn();
		
		}
			
		//robot read Label item
		private String robotReadlabel(FxRobot robot, String id)
		{
			return robot.lookup(id).queryAs(Label.class).getText();
		}
		
		//robot read text item
		private String robotReadText(FxRobot robot, String id)
		{
			return robot.lookup(id).queryAs(Text.class).getText();
		}
		
		//robot read text item
		private void robotAddMessage(FxRobot robot, String text)
		{
			robotTypeInText(robot, text, "#CRnewMessage");
			robot.clickOn("#CRsendME");// will click submit
			
		}
		
		//robot log in and send a message then log out
		private void robotSendoneMessage(FxRobot robot,String username, String password,  int cRnum,String mTxt)
		{
			robotLogIn(robot,username,password);
			robotselectChatRoomItem(robot,cRnum);
			robotAddMessage(robot,mTxt);
			robotLogIOut(robot); //from chat room
		}
		//robot read text item
		private void robotAddnewUser(FxRobot robot,String username, String password)
		{
			robot.clickOn("#LoginNewUser");
			robotLogIn(robot,username,password);
		}
		
		//robot log in and send a message then log out
		private void robotSendoneMFromfake(FxRobot robot, ArrayList<Person> fakepeople, int per ,String mTxt)
		{
			robotLogIn(robot,fakepeople.get(per).getUsername(),fakepeople.get(per).getPassword());
			robotselectChatRoomItem(robot,2);
			robotAddMessage(robot,mTxt);
			robotLogIOut(robot); //from chat room
		}
		private void clientNew() {
			Client MainClient = new Client(100000001);
			MainClient.encodeToXML(("ClientID" + "100000001" + ".xml")); //creates first save
		}
		
		private void clientReset() {
			//get file ...
			Client TheUnEditedflie = Client.decodeFromXML("ClientTestFile.xml");
			
			//reset as the file to user for testing
			TheUnEditedflie.encodeToXML(("ClientID" + "100000001" + ".xml"));
			
		}
		
		@Test
		public void testAll(FxRobot robot) throws InterruptedException {
			// reset the main file for testing
				//clientNew();
				clientReset();
				
				Thread.sleep(2000); // -- 2 sec
				
				//all user
					//Person ExampleUserA = new Person("ExampleUserA","PasswordA",true);
				 	//Person ExampleUserB = new Person("ExampleUserB","PasswordB",false);
				 	//Person ExampleUserC = new Person("ExampleUserC","PasswordC",false);
				 	//Person ExampleUserD = new Person("ExampleUserD","PasswordD",false);
				 	//Person ExampleUserE = new Person("ExampleUserE","PasswordE",false);
				 	//Person ExampleUserF = new Person("ExampleUserF","PasswordF",false);
					//king,king
					//why,why 	
				//robotAddnewUser(robot,"king","king");
				//robotAddnewUser(robot,"whys","whys");
				
				ChatRoom TestChatRoomC = new ChatRoom("The new way","king");
				Client TheUnEditedflie = Client.decodeFromXML("ClientTestFile.xml");
				TheUnEditedflie.getLocalRooms().add(TestChatRoomC);
				TheUnEditedflie.encodeToXML(("ClientID" + "100000001" + ".xml"));
				
			//array of names
				String[] fakepeoplenames = {"SweetKristy","KristyHoney", "BubblySnowflake", 
											"abyKrisfly","blassmedal","madelinecouch","divvychog",
											"licPrincess","tyButter","FairyPrince"};
			//array list of people...
				ArrayList<Person> fakepeople = new ArrayList<Person>(); // array list 
				
			//make each name in to a person.. and has them log in
				for (int i = 0; i < fakepeoplenames.length; i++) {
					fakepeople.add((new Person(fakepeoplenames[i],("PW"+i),false)));
					robotAddnewUser(robot,fakepeoplenames[i],("PW"+i));
				}
			//send fake message
				robotSendoneMFromfake(robot,fakepeople,0,"hello, everyone");
				robotSendoneMFromfake(robot,fakepeople,0,"Roll call");
				for (int i = 0; i < fakepeoplenames.length; i++) {
					robotSendoneMFromfake(robot,fakepeople,i,("hi im " + fakepeoplenames[i]));
				}
				// here are some thing you should know to make a better test
				
				robotSendoneMessage(robot,"king","king",2,"I'm gald everyone is here, lets get started");	
				robotSendoneMessage(robot,"why","why",2,"I agree");
				robotSendoneMessage(robot,"king","king",2,"Lets start at step 0, is everyone ready ... ");
				robotSendoneMessage(robot,"why","why",2,"I'd Like to hope so");
				robotSendoneMessage(robot,"king","king",2,"ok. it's time ... to make a quick chat room app for dum dums");
				
				Thread.sleep(2000); // -- 2 sec
		}
	
	
	
}

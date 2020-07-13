package app;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Person;
import views.LoginViewController;

public class Main extends Application {
	
	
	
	@Override
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
	
	
	public static void main (String [] args) {
		launch(args);
		//run it
	}
	
}

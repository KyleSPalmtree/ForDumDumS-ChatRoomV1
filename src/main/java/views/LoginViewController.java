package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Client;

public class LoginViewController {

	//Variables
	Client UserclientModel;
	Stage stage;
	
	//object Methods
	public void setStage(Stage Thestage)
    {
		stage=Thestage;
    }
	
	@FXML
    void onClickNewUser(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/NewUserView.fxml")); 
		BorderPane MainView = loader.load(); //Get view
		NewUserViewController ControllerA = loader.getController(); //Get controller
		ControllerA.setStage(stage);//the stage
	//Loading up the main Scene
		Scene s = new Scene(MainView);
		stage.setTitle("ChatRooms"); 
		stage.setScene(s);
		stage.show();
		
    }

    @FXML
    void onClickSubmit(ActionEvent event) throws IOException {
    		infotext.setText(""); 	
    	//Loading
    		infotext.setText("Loading ...");
    		infotext.setFill(Color.BLACK);
    	int ClientID = Integer.parseInt(LoginClientID.getText()) ; //getting Id
    		infotext.setText("Getting ClientID"); 
    		infotext.setFill(Color.BLACK);
		Client MainClient = Client.decodeFromXML(("ClientID" + (ClientID) + ".xml")); // Loading client - in the future it will make new ones to
			System.out.println("Client: The Client was Opened Successfully "+ ClientID);
			infotext.setText("ClientID Was Opened Successfully"); 
			infotext.setFill(Color.GREEN);
		UserclientModel = MainClient;
			infotext.setText("Trying to login"); 
			infotext.setFill(Color.YELLOW);
		if(MainClient.loginToClient(LoginUserName.getText(),LoginPassWord.getText())) 
		{
			infotext.setText("Logined Successfully"); 
			infotext.setFill(Color.GREEN);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../views/MainView.fxml")); 
			BorderPane MainView = loader.load(); //Get view
			MainViewController ControllerA = loader.getController(); //Get controller
			ControllerA.setStage(stage);//the stage
			ControllerA.setClient(UserclientModel);//the Client
			ControllerA.setView(MainView);
		//Loading up the main Scene
			Scene s = new Scene(MainView);
			stage.setTitle("ChatRooms"); 
			stage.setScene(s);
			stage.show();
			
			
			
			
		}else {
			infotext.setText("UserName or PassWord does not match, or was not found"); 
			infotext.setFill(Color.RED);
			LoginUserName.setText(""); // set them empty
			LoginPassWord.setText("");
		}
    }
    @FXML // fx:id="infotext"
    private Text infotext; // Value injected by FXMLLoader
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="LoginLabel"
    private Label LoginLabel; // Value injected by FXMLLoader

    @FXML // fx:id="LoginUserName"
    private TextField LoginUserName; // Value injected by FXMLLoader

    @FXML // fx:id="LoginPassWord"
    private PasswordField LoginPassWord; // Value injected by FXMLLoader

    @FXML // fx:id="LoginClientID"
    private TextField LoginClientID; // Value injected by FXMLLoader

    @FXML // fx:id="LoginNewUser"
    private Button LoginNewUser; // Value injected by FXMLLoader

    @FXML // fx:id="LoginSubmit"
    private Button LoginSubmit; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert LoginLabel != null : "fx:id=\"LoginLabel\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert LoginUserName != null : "fx:id=\"LoginUserName\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert LoginPassWord != null : "fx:id=\"LoginPassWord\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert LoginClientID != null : "fx:id=\"LoginClientID\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert LoginNewUser != null : "fx:id=\"LoginNewUser\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert LoginSubmit != null : "fx:id=\"LoginSubmit\" was not injected: check your FXML file 'LoginView.fxml'.";

    }
}

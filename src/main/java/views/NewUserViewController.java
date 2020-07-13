/**
 * Sample Skeleton for 'NewUserView.fxml' Controller Class
 */

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Client;

public class NewUserViewController {

	//Variables
	Client UserclientModel;
	Stage stage;
	
	//object Methods
	public void setStage(Stage Thestage)
    {
		stage=Thestage;
		infotext.setText("Making a new user will log you out if you are log in"); 
		infotext.setFill(Color.BLACK);
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
			infotext.setText("Trying to Make user"); 
			infotext.setFill(Color.YELLOW);
		if(UserclientModel.newUserloggingIn(LoginUserName.getText(), LoginPassWord.getText(), wantsTobeadmit.isSelected())) 
		{
			infotext.setText("New User Was Made"); 
			infotext.setFill(Color.GREEN);
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../views/LoginView.fxml")); 
			BorderPane LoginView = loader.load(); //Get view
			LoginViewController ControllerA = loader.getController(); //Get controller
			ControllerA.setStage(stage);//the stage
			UserclientModel.logoutofClient();
		//Loading up the main Scene
			Scene s = new Scene(LoginView);
			stage.setTitle("LOGIN"); 
			stage.setScene(s);
			stage.show();
		}else {
			infotext.setText("The UserName you have requested is already in taken, try again"); 
			infotext.setFill(Color.RED);	
		}
    }
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="LoginLabel"
    private Label LoginLabel; // Value injected by FXMLLoader

    @FXML // fx:id="infotext"
    private Text infotext; // Value injected by FXMLLoader

    @FXML // fx:id="LoginClientID"
    private TextField LoginClientID; // Value injected by FXMLLoader
    
    @FXML // fx:id="LoginUserName"
    private TextField LoginUserName; // Value injected by FXMLLoader

    @FXML // fx:id="LoginPassWord"
    private PasswordField LoginPassWord; // Value injected by FXMLLoader

    @FXML // fx:id="wantsTobeadmit"
    private CheckBox wantsTobeadmit; // Value injected by FXMLLoader

    @FXML // fx:id="LoginSubmit"
    private Button LoginSubmit; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert LoginLabel != null : "fx:id=\"LoginLabel\" was not injected: check your FXML file 'NewUserView.fxml'.";
        assert infotext != null : "fx:id=\"infotext\" was not injected: check your FXML file 'NewUserView.fxml'.";
        assert LoginUserName != null : "fx:id=\"LoginUserName\" was not injected: check your FXML file 'NewUserView.fxml'.";
        assert LoginPassWord != null : "fx:id=\"LoginPassWord\" was not injected: check your FXML file 'NewUserView.fxml'.";
        assert wantsTobeadmit != null : "fx:id=\"wantsTobeadmit\" was not injected: check your FXML file 'NewUserView.fxml'.";
        assert LoginSubmit != null : "fx:id=\"LoginSubmit\" was not injected: check your FXML file 'NewUserView.fxml'.";

    }
}

/**
 * Sample Skeleton for 'MainView.fxml' Controller Class
 */

package views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ChatRoom;
import main.Client;

public class MainViewController {

	//Variables
		Client UserclientModel;
		Stage stage;
		BorderPane thisView;
		
	public ObservableList<ChatRoom> CRList= FXCollections.observableArrayList();
	
	//object Methods
	public void setStage(Stage Thestage)
    {
		stage=Thestage;
    }
	
	public void setView(BorderPane theView)
    {
		thisView=theView;
    }
	
	public void setClient(Client Theclient)
    {
		UserclientModel=Theclient; // set the client
		
		for (int i=0; i<UserclientModel.getLocalRooms().size();i++){
			CRList.add(UserclientModel.getLocalRooms().get(i));
		}
		MainChatList.setItems(CRList); 

    }
	 
	@FXML
    void onClickNewRoom(ActionEvent event) {
		System.out.println("new");
    }
	
    @FXML
    void onClickChatRoom(ActionEvent event) throws IOException {
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
    }

    @FXML
    void onClickNewUser(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/NewUserView.fxml")); 
		BorderPane MainView = loader.load(); //Get view
		NewUserViewController ControllerA = loader.getController(); //Get controller
		ControllerA.setStage(stage);//the stage
	//Loading up the main Scene
		
		//we need this view, lets get it
		thisView.setCenter(MainView);
		stage.setTitle("NewUser"); 
    }
    
    @FXML
    void onListClicked(MouseEvent event) throws IOException {
    	UserclientModel.setCurrentChatRoom(MainChatList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/ChatRoomView.fxml")); 
		BorderPane MainView = loader.load(); //Get view
		ChatRoomViewController ControllerA = loader.getController(); //Get controller
		ControllerA.setStage(stage);//the stage
		ControllerA.setClient(UserclientModel);//the stage
	//Loading up the main Scene
		Scene s = new Scene(MainView);
		stage.setTitle("ChatRooms"); 
		stage.setScene(s);
		stage.show();
		

    }
    
    @FXML // fx:id="MainChatbutt"
    private Button MainChatbutt; // Value injected by FXMLLoader
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="MainTitleText"
    private Text MainTitleText; // Value injected by FXMLLoader

    @FXML // fx:id="MainChatList"
    private ListView<ChatRoom> MainChatList; // Value injected by FXMLLoader

    @FXML // fx:id="MainNewRoom"
    private Button MainNewRoom; // Value injected by FXMLLoader

    @FXML // fx:id="MainNewUser"
    private Button MainNewUser; // Value injected by FXMLLoader

   
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert MainTitleText != null : "fx:id=\"MainTitleText\" was not injected: check your FXML file 'MainView.fxml'.";
        assert MainChatList != null : "fx:id=\"MainChatList\" was not injected: check your FXML file 'MainView.fxml'.";
        assert MainNewRoom != null : "fx:id=\"MainNewRoom\" was not injected: check your FXML file 'MainView.fxml'.";
        assert MainNewUser != null : "fx:id=\"MainNewUser\" was not injected: check your FXML file 'MainView.fxml'.";

    }
}

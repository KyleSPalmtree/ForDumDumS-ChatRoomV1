/**
 * Sample Skeleton for 'ChatRoomView.fxml' Controller Class
 */

package views;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.ChatRoom;
import main.Client;
import main.Message;

public class ChatRoomViewController {
	//Variables
		Client UserclientModel;
		Stage stage;
		
	public ObservableList<Message> CRList= FXCollections.observableArrayList();
	
	//object Methods
	public void setStage(Stage Thestage)
    {
		stage=Thestage;
    }
	
	public void setClient(Client Theclient)
    {
		UserclientModel=Theclient; // set the client
		
		for (int i=0; i<UserclientModel.getCurrentChatRoom().getMessages().size();i++){
			CRList.add(UserclientModel.getCurrentChatRoom().getMessages().get(i));
		}
		CRChat.setItems(CRList); 
    }
	
    @FXML
    void onClickBack(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/MainView.fxml")); 
		BorderPane MainView = loader.load(); //Get view
		MainViewController ControllerA = loader.getController(); //Get controller
		ControllerA.setStage(stage);//the stage
		ControllerA.setClient(UserclientModel);//the Client
		ControllerA.setView(MainView);//the views
	//Loading up the main Scene
		Scene s = new Scene(MainView);
		stage.setTitle("ChatRooms"); 
		stage.setScene(s);
		stage.show();
    }

    @FXML
    void onClickSendMessage(ActionEvent event) {
    	
    	UserclientModel.addMessagetocurrChatRoom(CRnewMessage.getText()); // will add the message
    	
    	//this will update the view
    	CRList= FXCollections.observableArrayList();// will reset it
    	for (int i=0; i<UserclientModel.getCurrentChatRoom().getMessages().size();i++){
			CRList.add(UserclientModel.getCurrentChatRoom().getMessages().get(i));
		}
		CRChat.setItems(CRList); 
		CRnewMessage.setText("");
    	
    }
    

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="CRback"
    private Button CRback; // Value injected by FXMLLoader

    @FXML // fx:id="CRChat"
    private ListView<Message> CRChat; // Value injected by FXMLLoader

    @FXML // fx:id="CRnewMessage"
    private TextArea CRnewMessage; // Value injected by FXMLLoader

    @FXML // fx:id="CRsendME"
    private Button CRsendME; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert CRback != null : "fx:id=\"CRback\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
        assert CRChat != null : "fx:id=\"CRChat\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
        assert CRnewMessage != null : "fx:id=\"CRnewMessage\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
        assert CRsendME != null : "fx:id=\"CRsendME\" was not injected: check your FXML file 'ChatRoomView.fxml'.";

    }
}


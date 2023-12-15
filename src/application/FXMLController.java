package application;

import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLController implements Initializable{
	
	  @FXML
	    private Button loginBtn;

	    @FXML
	    private AnchorPane main_form;

	    @FXML
	    private TextField password;

	    @FXML
	    private TextField username;

	    
	    private Connection connect;
	    private PreparedStatement prepare;
	    private ResultSet result;
	    
	    public void login() {
	    	String sql="SELECT * FROM users WHERE username=? and password=?";
	    	connect=database.connectDb();
	    	
	    	try {
	    		prepare=connect.prepareStatement(sql);
	    		prepare.setString(1, username.getText());
	    		prepare.setString(2, password.getText());
	    		result=prepare.executeQuery();
	    		Alert alert;
	    		
	    		if(username.getText().isEmpty()|| password.getText().isEmpty()) {
	    			alert=new Alert(AlertType.ERROR);
	    			alert.setTitle("Error");
	    			alert.setContentText("Please fill all blank fields");
	    			alert.showAndWait();
	    		}
	    		
	    		else {
	    			if(result.next()) {
	    				
	    				data.username=username.getText();
	    				
	    				alert=new Alert(AlertType.INFORMATION);
		    			alert.setTitle("Information");
		    			alert.setContentText("Succesfully Login!");
		    			alert.showAndWait();
		    			
		    			
		    			//HIDE THE LOGIN FORM
		    			loginBtn.getScene().getWindow().hide();
		    			
		    			
		    			//LINK THE DASHBOARD
		    			Parent root=FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		    			
		    			Stage stage=new Stage();
		    			Scene scene=new Scene(root);
		    			stage.setScene(scene);
		    			stage.show();
	    				
	    				
	    			}
	    			else {
	    				
	    				//IF WRONG USERNAME OR PASSWORD YOU HAVE ENTERED
	    				
	    				alert=new Alert(AlertType.ERROR);
		    			alert.setTitle("Error");
		    			alert.setContentText("Check the password and username");
		    			alert.showAndWait();
						
					}
	    		}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    }
	    
	    
	
	    
	




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

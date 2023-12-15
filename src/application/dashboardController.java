package application;

import java.sql.Statement;
import java.io.NotActiveException;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

//import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class dashboardController implements Initializable {

	
	
	
    @FXML
    private AnchorPane main_form;
    
    
    

    @FXML
    private TableView<categories> availableFD_tableView;
	
	
    @FXML
    private Button availableFD_btn;

    @FXML
    private Button avaliableFD_addBtn;

    @FXML
    private Button avaliableFD_clearBtn;

    @FXML
    private TableColumn<categories, String> avaliableFD_col_price;

    @FXML
    private TableColumn<categories, String> avaliableFD_col_productID;

    @FXML
    private TableColumn<categories, String> avaliableFD_col_productName;

    @FXML
    private TableColumn<categories, String> avaliableFD_col_status;

    @FXML
    private TableColumn<categories, String> avaliableFD_col_type;

    @FXML
    private Button avaliableFD_deleteBtn;

    @FXML
    private AnchorPane avaliableFD_form;

    @FXML
    private TextField avaliableFD_productID;

    @FXML
    private TextField avaliableFD_productName;

    @FXML
    private TextField avaliableFD_productPrice;

    @FXML
    private ComboBox<?> avaliableFD_productStatus;

    @FXML
    private ComboBox<?> avaliableFD_productType;

    @FXML
    private TextField avaliableFD_search;

    @FXML
    private Button avaliableFD_updateBtn;

    @FXML
    private Button dashboard_Btn;

    @FXML
    private BarChart<?, ?> dashboard_ICChart;

    @FXML
    private Label dashboard_NC;

    @FXML
    private BarChart<?, ?> dashboard_NOCChart;

    @FXML
    private Label dashboard_TDIncome;

    @FXML
    private Label dashboard_TI;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button btn_logout;

    @FXML
    private ComboBox<?> order_ProductID;

    @FXML
    private ComboBox<?> order_ProductName;

    @FXML
    private Button order_addBtn;

    @FXML
    private TextField order_amount;

    @FXML
    private Button order_btn;

    @FXML
    private TableColumn<?, ?> order_col_price;

    @FXML
    private TableColumn<?, ?> order_col_productID;

    @FXML
    private TableColumn<?, ?> order_col_productName;

    @FXML
    private TableColumn<?, ?> order_col_quantity;

    @FXML
    private TableColumn<?, ?> order_col_type;

    @FXML
    private AnchorPane order_form;

    @FXML
    private Button order_payBtn;

    @FXML
    private Spinner<?> order_quantity;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_removeBtn;

    @FXML
    private TableView<?> order_tableView;

    @FXML
    private Label order_total;

    @FXML
    private Label username;
    
    
    private Connection connection;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    public void availableFDAdd() {
    	String sql="INSERT INTO category (product_id,product_name,type,price,status)"+
    "VALUES(?,?,?,?,?,?)";
    	
    	connection=database.connectDb();
    	try {
    		prepare=connection.prepareStatement(sql);
    		prepare.setString(1, avaliableFD_productID.getText());
    		prepare.setString(2, avaliableFD_productName.getText());
    		prepare.setString(3, (String)avaliableFD_productType.getSelectionModel().getSelectedItem()); //BURAYA TEKRAR BAK
    		prepare.setString(4, avaliableFD_productPrice.getText());
    		prepare.setString(5, (String)avaliableFD_productStatus.getSelectionModel().getSelectedItem());
    		
    		Alert alert;
    		
    		if(avaliableFD_productID.getText().isEmpty() 
    				|| avaliableFD_productName.getText().isEmpty()
    				|| avaliableFD_productType.getSelectionModel()==null
    		|| avaliableFD_productPrice.getText().isEmpty()
			|| avaliableFD_productStatus.getSelectionModel()==null) {
    			alert=new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();			
    		}
    		
    		else {
    			String checkdata="SELECT product_id FROM category WHERE product_id='"
    		+avaliableFD_productID.getText()+"'";
    			
    			connection=database.connectDb();
    			statement=connection.createStatement();
    			result=statement.executeQuery(checkdata);
    			
    			if(result.next()) {
    				alert=new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Product ID: "+avaliableFD_productID.getText()+"is already exist!");
        			
        			alert.showAndWait();			
    				
    			}else {
    				prepare.executeQuery();
    				alert=new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Added!");
        			alert.showAndWait();
				}				
			}
    		
    			
    		
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    
    public void close() {
    	System.exit(0);
    }
    public void minimize() {
    	Stage stage= (Stage)main_form.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    public void displayUsername() {
    	String user=data.username;
    	user=user.substring(0,1).toUpperCase()+user.substring(1);
    	username.setText(user);
    }
    
    
    
    
    public ObservableList<categories> availableFDListData(){
    	ObservableList<categories> listData=FXCollections.observableArrayList();
    	
    	String sql="SELECT * FROM category";
    	connection=database.connectDb();
    	
    	try {
    		prepare=connection.prepareStatement(sql);
    		result=prepare.executeQuery();
    		
    		categories cat;
    		
    		while (result.next()) {
				cat=new categories(result.getString("product_id"), result.getString("product_name"), result.getString("type"), result.getDouble("price"), result.getString("status"));
				listData.add(cat);
				
			}
    		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
    	
    	return listData;
    }
    
    
    private ObservableList<categories> availableFDList;
    
    public void availableFDShowData() {
    	availableFDList=availableFDListData();
    	
    	
    	avaliableFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
    	avaliableFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	avaliableFD_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    	avaliableFD_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	avaliableFD_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	availableFD_tableView.setItems(availableFDList);
    	
    	
    }
    
    
    
    private String[] categories= {"Meals","Drinks"};
    
    public void availableFDType() {
    	List<String> listCat=new ArrayList<>();
    	for(String data:categories) {
    		listCat.add(data);
    	}
    	
    	ObservableList listData= FXCollections.observableArrayList(listCat);
    }
    
    
    
    
    private String[] status= {"Available","Not Available"};
    
    public  void availableFDStatus() {
    	List<String> listStatus=new ArrayList<String>();
    	
    	for(String data: status) {
    		listStatus.add(data);
    	}
    	
    	ObservableList listData=FXCollections.observableArrayList(listStatus);
    	avaliableFD_productStatus.setItems(listData);
    	
	}
    
    public void switchForm(ActionEvent event) {
    	if(event.getSource()==dashboard_Btn) {
    		dashboard_form.setVisible(true);
    		avaliableFD_form.setVisible(false);
    		order_form.setVisible(false);
    		
    		dashboard_Btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width:0;");
    		availableFD_btn.setStyle("-fx-background-color: transparent; -fx-border-width:1; -fx-text-fill: #000;");
    		order_btn.setStyle("-fx-background-color: transparent; -fx-border-width:1; -fx-text-fill: #000;");
    		
    	}
    	else if(event.getSource()==availableFD_btn) {
    		dashboard_form.setVisible(false);
    		avaliableFD_form.setVisible(true);
    		order_form.setVisible(false);
    		
    		
    		availableFD_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width:0;");
    		dashboard_Btn.setStyle("-fx-background-color: transparent; -fx-border-width:1; -fx-text-fill: #000;");
    		order_btn.setStyle("-fx-background-color: transparent; -fx-border-width:1; -fx-text-fill: #000;");
    	}
    	else if(event.getSource()==order_btn) {
    		dashboard_form.setVisible(false);
    		avaliableFD_form.setVisible(false);
    		order_form.setVisible(true);
    		
    		

    		order_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width:0;");
    		dashboard_Btn.setStyle("-fx-background-color: transparent; -fx-border-width:1; -fx-text-fill: #000;");
    		availableFD_btn.setStyle("-fx-background-color: transparent; -fx-border-width:1; -fx-text-fill: #000;");
    	}
    }
    
    
	public void logout() {
    	try {
    		Alert alert=new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmation Message");
    		alert.setHeaderText("Are you sure you want to logout?");
    		Optional<ButtonType> option=alert.showAndWait();
    	
    		if (option.get().equals(ButtonType.OK)) {
    			btn_logout.getScene().getWindow().hide();
    			Parent root=FXMLLoader.load(getClass().getResource("document.fxml"));
    			Stage stage=new Stage();
    			Scene scene=new Scene(root);
    			stage.setScene(scene);
    			stage.show();
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		displayUsername();
		availableFDStatus();
		availableFDType();
		
		
	}
	
	

}

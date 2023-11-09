package application;

import java.sql.ResultSet;
import java.io.IOException;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	Stage dialogStage = new Stage();
    Scene scene;
    
    Connection connection = null;
    ResultSet resultSet = null;
 
	
	@FXML
	public void Login(ActionEvent event) throws IOException{
    
        try{
        	java.sql.Connection c = (Connection) teskoneksi.configDB();
        	String sql = "SELECT * FROM logindata WHERE username = '"+txtUsername.getText() +"' AND password = '"+txtPassword.getText() +"'";
            java.sql.Statement s = c.createStatement();
            resultSet = s.executeQuery(sql);
            if(!resultSet.next()){
            	lblStatus.setText("Login Gagal");
            }else{
            	lblStatus.setText("Login Sukses");
            	
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource("/application/halamanUtama.fxml"));
    			Scene halamanUtamaScene = new Scene (halamanUtamaParent);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
    			app_stage.setScene(halamanUtamaScene);
    			app_stage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
}

package application;

import java.sql.ResultSet;
import java.io.IOException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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
        	String sql = "SELECT * FROM logindata WHERE username = ? AND password = ?";
			PreparedStatement stmt = (PreparedStatement) c.prepareStatement(sql);
        	stmt.setString(1, txtUsername.getText());
        	stmt.setString(2, txtPassword.getText());
            resultSet = stmt.executeQuery();
            if(!resultSet.next()){
            	lblStatus.setText("Login Gagal");
            }else{
            	lblStatus.setText("Login Sukses");
            	
            	String resource = resultSet.getInt("level") == 2 ? "halamanutama.fxml" : "halamanutama1.fxml";
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource(resource));
    			Scene halamanUtamaScene = new Scene (halamanUtamaParent);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
    			app_stage.setMaximized(true);
    			app_stage.setScene(halamanUtamaScene);
    			app_stage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
}

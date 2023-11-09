package application;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController6 implements Initializable{
	@FXML
	private Spinner<Integer> spnNomor;
	
	@FXML
	private Spinner<Integer> spnLevel;
	
	@FXML
	private TextField txtNama;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;

	@FXML
	private Spinner<Integer> spnDrNomor;
	
	@FXML
	private Spinner<Integer> spnDrLevel;
	
	@FXML
	private TextField txtDrNama;
	
	@FXML
	private TextField txtDrUsername;
	
	@FXML
	private TextField txtDrPassword;
	
	@FXML
	private Spinner<Integer> spnrNomor;
	
	@FXML
	private TextField txtrRuang;
	
	@FXML
	private Button btnSimpan;
	
	@FXML
	private Button btnDrSimpan;
	
	@FXML
	private Button btnrSimpan;
	
	@FXML
	private Button btnTutup;
	
	@FXML
	public void Tutup(ActionEvent event) throws IOException{
    
        try{       	
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource("/application/halamanutama2.fxml"));
    			Scene halamanUtamaScene = new Scene (halamanUtamaParent);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
    			app_stage.setScene(halamanUtamaScene);
    			app_stage.show();    
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
	
	 Connection connection = null;
	 PreparedStatement  stmt = null;
	
	 	public void setNomor1 (Object object) {
			spnNomor.getEditor().setText((String) object);
		}
	 	
	 	public void setLevel1 (Object object) {
			spnLevel.getEditor().setText((String) object);
		}
		
		public void setNama1 (Object object) {
			txtNama.setText((String) object);
		}
		
		public void setUsername1 (Object object) {
			txtUsername.setText((String) object);
		}
		
		public void setPassword1 (Object object) {
			txtPassword.setText((String) object);
		}
		
		public void setNomor2 (Object object) {
			spnDrNomor.getEditor().setText((String) object);
		}
	 	
	 	public void setLevel2 (Object object) {
			spnDrLevel.getEditor().setText((String) object);
		}
		
		public void setNama2 (Object object) {
			txtDrNama.setText((String) object);
		}
		
		public void setUsername2 (Object object) {
			txtDrUsername.setText((String) object);
		}
		
		public void setPassword2 (Object object) {
			txtDrPassword.setText((String) object);
		}
		
		public void setNomor3(Object object) {
			spnrNomor.getEditor().setText((String) object);
		}
		
		public void setRuang3(Object object) {
			txtrRuang.setText((String) object);
		}
		
	@FXML
	public void Simpan(ActionEvent event) throws IOException, SQLException{
		java.sql.Connection con = (Connection) teskoneksi.configDB();
    	String sql = "UPDATE logindata SET nomor= ?, level = ?, nama = ?, username = ?, password = ? WHERE nomor= '"+spnNomor.getEditor().getText()+"'";
    	stmt = (PreparedStatement) con.prepareStatement(sql);
    	stmt.setString(1, ((TextField)spnNomor.getEditor()).getText());
    	stmt.setString(2, ((TextField)spnLevel.getEditor()).getText());
    	stmt.setString(3, txtNama.getText());
    	stmt.setString(4, txtUsername.getText());
    	stmt.setString(5, txtPassword.getText());
    	stmt.executeUpdate();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Berhasil");
    	alert.setHeaderText(null);
    	alert.setContentText("Data Pegawai telah berhasil diubah.");

    	alert.showAndWait();
	}
	
	@FXML
	public void drSimpan(ActionEvent event) throws IOException, SQLException{
		java.sql.Connection con = (Connection) teskoneksi.configDB();
    	String sql = "UPDATE dokterdata SET nomor= ?, level = ?, nama = ?, username = ?, password = ? WHERE nomor= '"+spnDrNomor.getEditor().getText()+"'";
    	stmt = (PreparedStatement) con.prepareStatement(sql);
    	stmt.setString(1, ((TextField)spnDrNomor.getEditor()).getText());
    	stmt.setString(2, ((TextField)spnDrLevel.getEditor()).getText());
    	stmt.setString(3, txtDrNama.getText());
    	stmt.setString(4, txtDrUsername.getText());
    	stmt.setString(5, txtDrPassword.getText());
    	stmt.executeUpdate();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Berhasil");
    	alert.setHeaderText(null);
    	alert.setContentText("Data Dokter telah berhasil diubah.");

    	alert.showAndWait();
	}
	
	@FXML
	public void rSimpan(ActionEvent event) throws IOException, SQLException{
		java.sql.Connection con = (Connection) teskoneksi.configDB();
    	String sql = "UPDATE ruangdata SET nomor= ? AND ruang = ? WHERE nomor= '"+spnrNomor.getEditor().getText()+"'";
    	stmt = (PreparedStatement) con.prepareStatement(sql);
    	stmt.setString(1, spnrNomor.getEditor().getText());
    	stmt.setString(2, txtrRuang.getText());
    	stmt.executeUpdate();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Berhasil");
    	alert.setHeaderText(null);
    	alert.setContentText("Data Ruang telah berhasil diubah.");

    	alert.showAndWait();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		spnNomor.setValueFactory(valueFactory);
		SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, 0);
		spnLevel.setValueFactory(valueFactory1);
		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		spnDrNomor.setValueFactory(valueFactory2);
		SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, 0);
		spnDrLevel.setValueFactory(valueFactory3);
		SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		spnrNomor.setValueFactory(valueFactory4);
		
	}
	
}


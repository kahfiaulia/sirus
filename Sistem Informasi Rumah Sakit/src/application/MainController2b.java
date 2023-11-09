package application;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController2b implements Initializable{
	@FXML
	private TableView<Pegawai> tblPegawai;
	@FXML
	private TableColumn<Pegawai, Integer> nomor;
	@FXML
	private TableColumn<Pegawai, Integer> level;
	@FXML
	private TableColumn<Pegawai, String> nama;
	@FXML
	private TableColumn<Pegawai, String> username;
	@FXML
	private TableColumn<Pegawai, String> password;
	
	@FXML
	private TableView<Dokter> tblDokter;
	@FXML
	private TableColumn<Dokter, Integer> drNomor;
	@FXML
	private TableColumn<Dokter, Integer> drLevel;
	@FXML
	private TableColumn<Dokter, String> drNama;
	@FXML
	private TableColumn<Dokter, String> drUsername;
	@FXML
	private TableColumn<Dokter, String> drPassword;
	
	@FXML
	private TableView<Ruang> tblRuang;
	@FXML
	private TableColumn<Ruang, Integer> rNomor;
	@FXML
	private TableColumn<Ruang, String> rRuang;
	
	@FXML
	public ObservableList<Pegawai> list = FXCollections.observableArrayList();
	
	@FXML
	public ObservableList<Dokter> drList = FXCollections.observableArrayList();
	
	@FXML
	public ObservableList<Ruang> rList = FXCollections.observableArrayList();
	
	@FXML
	private Button btnTambah;
	
	@FXML
	private Button btnUbah;
	
	@FXML
	private Button btnHapus;
	
	@FXML
	private Button btnCari;
	
	@FXML
	private TextField txtCari;
	
	@FXML
	private Button btnRefresh;
	
	@FXML
	private Button btnLogout;
	
	@FXML
	public void Refresh(ActionEvent event) throws IOException{
		tblPegawai.getItems().clear();
		tblDokter.getItems().clear();
		tblRuang.getItems().clear();
		
		tblPegawai.getSelectionModel().clearSelection();
		tblDokter.getSelectionModel().clearSelection();
		tblRuang.getSelectionModel().clearSelection();
		
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM logindata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        txtCari.clear();
	        
	        while (rs.next()) {
	        	list.add(new Pegawai(rs.getInt("nomor"), rs.getInt("level"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
		
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM dokterdata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        
	        while (rs.next()) {
	        	drList.add(new Dokter(rs.getInt("nomor"), rs.getInt("level"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
	        
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
		
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM ruangdata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        
	        while (rs.next()) {
	        	rList.add(new Ruang(rs.getInt("nomor"), rs.getString("ruang")));
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
	}
	
	@FXML
	public void Logout(ActionEvent event) throws IOException{
    
        try{    
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource("/application/halamanUtama1.fxml"));
    			Scene halamanUtamaScene = new Scene (halamanUtamaParent);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
    			app_stage.setMaximized(true);
    			app_stage.setScene(halamanUtamaScene);
    			app_stage.show();    
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
	

	@FXML
	public void Ubah(ActionEvent event) throws IOException{
    
        try{    
        		Pegawai selectedItem = tblPegawai.getSelectionModel().getSelectedItem();
        		   			    			
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("ubahdata1.fxml"));
            	Parent root = (Parent) loader.load();
            	MainController6 secController = loader.getController();
            	secController.setNomor1(selectedItem.getNomor().toString());
            	secController.setLevel1(selectedItem.getLevel().toString());
            	secController.setNama1(selectedItem.getNama().toString());
            	secController.setUsername1(selectedItem.getUsername().toString());
            	secController.setPassword1(selectedItem.getPassword().toString());
            	
            	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            	app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
            	app_stage.setMaximized(true);
            	app_stage.setScene(new Scene(root));
            	app_stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        try{
    		Dokter selectedItem1 = tblDokter.getSelectionModel().getSelectedItem();
    		   			    			
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("ubahdata1.fxml"));
        	Parent root = (Parent) loader.load();
        	MainController6 secController = loader.getController();
        	
        	secController.setNomor2(selectedItem1.getNomor().toString());
        	secController.setLevel2(selectedItem1.getLevel().toString());
        	secController.setNama2(selectedItem1.getNama().toString());
        	secController.setUsername2(selectedItem1.getUsername().toString());
        	secController.setPassword2(selectedItem1.getPassword().toString());
        	
        	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
        	app_stage.setMaximized(true);
        	app_stage.setScene(new Scene(root));
        	app_stage.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
        
        try{
    		Ruang selectedItem2 = tblRuang.getSelectionModel().getSelectedItem();
    		   			    			
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("ubahdata1.fxml"));
        	Parent root = (Parent) loader.load();
        	MainController6 secController = loader.getController();
        	secController.setNomor3(selectedItem2.getNomor().toString());
        	secController.setRuang3(selectedItem2.getRuang().toString());
        	
        	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
        	app_stage.setMaximized(true);
        	app_stage.setScene(new Scene(root));
        	app_stage.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
	}

	@FXML
	public void Tambah(ActionEvent event) throws IOException{
    
        try{       	
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource("/application/tambahdata1.fxml"));
    			Scene halamanUtamaScene = new Scene (halamanUtamaParent);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setTitle("Sistem Informasi Rumah Sakit Terpadu");
    			app_stage.setMaximized(true);
    			app_stage.setScene(halamanUtamaScene);
    			app_stage.show();    
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
	
	 Connection connection = null;
	 PreparedStatement  stmt = null;
	 PreparedStatement  stmt1 = null;
	 PreparedStatement  stmt2 = null;
	 ResultSet resultSet = null;
	 
	 @FXML
		public void Cari(ActionEvent event) throws IOException{
	    
		 	try {
			    java.sql.Connection c = (Connection) teskoneksi.configDB();
		    	String sql = "SELECT * FROM logindata WHERE nama LIKE '%"+txtCari.getText() +"%'";
		        java.sql.Statement s = c.createStatement();
		        ResultSet rs = s.executeQuery(sql);
		        tblPegawai.getItems().clear();
		        while (rs.next()) {
		        	list.add(new Pegawai(rs.getInt("nomor"), rs.getInt("level"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
		        }
	           	
			} catch (SQLException e) {
				// TODO: handle exception
				 e.printStackTrace();
			}
		 	
		 	try {
				java.sql.Connection c = (Connection) teskoneksi.configDB();
		    	String sql = "SELECT * FROM dokterdata WHERE nama LIKE '%"+txtCari.getText() +"%'";
		        java.sql.Statement s = c.createStatement();
		        ResultSet rs = s.executeQuery(sql);
		        tblDokter.getItems().clear();
		        
		        while (rs.next()) {
		        	drList.add(new Dokter(rs.getInt("nomor"), rs.getInt("level"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
		        
		        }
			}
			 catch(Exception e){
		            e.printStackTrace();
		    }
		 	
		 	try {
				java.sql.Connection c = (Connection) teskoneksi.configDB();
		    	String sql = "SELECT * FROM ruangdata WHERE ruang LIKE '%"+txtCari.getText() +"%'";
		        java.sql.Statement s = c.createStatement();
		        ResultSet rs = s.executeQuery(sql);
		        tblRuang.getItems().clear();
		        
		        while (rs.next()) {
		        	rList.add(new Ruang(rs.getInt("nomor"), rs.getString("ruang")));
		        
		        }
			}
			 catch(Exception e){
		            e.printStackTrace();
		    }
		}
	
	@FXML
	public void Hapus(ActionEvent event) throws IOException, SQLException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Hapus Data");
		alert.setHeaderText(null);
		alert.setContentText("Apakah Anda yakin?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
				Pegawai selectedItem = tblPegawai.getSelectionModel().getSelectedItem();
				java.sql.Connection c = (Connection) teskoneksi.configDB();
			    String hapusSql = "DELETE FROM logindata WHERE nomor = ?";
			    stmt = (PreparedStatement) c.prepareStatement(hapusSql);
			    stmt.setInt(1, selectedItem.getNomor());
			    stmt.executeUpdate();
			    
			    tblPegawai.getItems().remove(selectedItem);
			    	
				Dokter selectedItem1 = tblDokter.getSelectionModel().getSelectedItem();
				java.sql.Connection c1 = (Connection) teskoneksi.configDB();
			    String hapusSql1 = "DELETE FROM dokterdata WHERE nomor = ?";
			    stmt1 = (PreparedStatement) c1.prepareStatement(hapusSql1);
			    stmt1.setInt(1, selectedItem1.getNomor());
			    stmt1.executeUpdate();
			    	
				tblDokter.getItems().remove(selectedItem1);
				
				Ruang selectedItem2 = tblRuang.getSelectionModel().getSelectedItem();
				java.sql.Connection c2 = (Connection) teskoneksi.configDB();
			    String hapusSql2 = "DELETE FROM ruangdata WHERE nomor = ?";
			    stmt2 = (PreparedStatement) c2.prepareStatement(hapusSql2);
			    stmt2.setInt(1, selectedItem2.getNomor());
			    stmt2.executeUpdate();
			    	
				tblRuang.getItems().remove(selectedItem2);
				
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		
		tblPegawai.getSelectionModel().clearSelection();
		tblDokter.getSelectionModel().clearSelection();
		tblRuang.getSelectionModel().clearSelection();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM logindata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        
	        while (rs.next()) {
	        	list.add(new Pegawai(rs.getInt("nomor"), rs.getInt("level"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
		
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM dokterdata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        
	        while (rs.next()) {
	        	drList.add(new Dokter(rs.getInt("nomor"), rs.getInt("level"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
	        
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
		
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM ruangdata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        
	        while (rs.next()) {
	        	rList.add(new Ruang(rs.getInt("nomor"), rs.getString("ruang")));
	        
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
		
		nomor.setCellValueFactory(new PropertyValueFactory<Pegawai, Integer>("nomor"));
		level.setCellValueFactory(new PropertyValueFactory<Pegawai, Integer>("level"));
		nama.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("nama"));
		username.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("username"));
		password.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("password"));
		tblPegawai.setItems(list);
		
		drNomor.setCellValueFactory(new PropertyValueFactory<Dokter, Integer>("nomor"));
		drLevel.setCellValueFactory(new PropertyValueFactory<Dokter, Integer>("level"));
		drNama.setCellValueFactory(new PropertyValueFactory<Dokter, String>("nama"));
		drUsername.setCellValueFactory(new PropertyValueFactory<Dokter, String>("username"));
		drPassword.setCellValueFactory(new PropertyValueFactory<Dokter, String>("password"));
		tblDokter.setItems(drList);
		
		rNomor.setCellValueFactory(new PropertyValueFactory<Ruang, Integer>("nomor"));
		rRuang.setCellValueFactory(new PropertyValueFactory<Ruang, String>("ruang"));
		tblRuang.setItems(rList);
	}
}

package application;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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

public class MainController2 implements Initializable{
	@FXML
	private TableView<Pasien> tblPasien;
	@FXML
	private TableColumn<Pasien, Integer> nomor;
	@FXML
	private TableColumn<Pasien, String> nama;
	@FXML
	private TableColumn<Pasien, String> jenisKelamin;
	@FXML
	private TableColumn<Pasien, Integer> usia;
	@FXML
	private TableColumn<Pasien, String> ruang;
	@FXML
	private TableColumn<Pasien, String> penyakit;
	@FXML
	private TableColumn<Pasien, String> dokter;
	@FXML
	private TableColumn<Pasien, Date> masuk;
	@FXML
	private TableColumn<Pasien, Date> keluar;
	@FXML
	private TableColumn<Pasien, Integer> biaya;
	
	@FXML
	public ObservableList<Pasien> list = FXCollections.observableArrayList();
	
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
		tblPasien.getItems().clear();
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM pasiendata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        txtCari.clear();
	        
	        while (rs.next()) {
	        	list.add(new Pasien(rs.getInt("nomor"), rs.getString("nama"), rs.getString("jeniskelamin"), rs.getInt("usia"), rs.getString("ruang"),rs.getString("penyakit"),rs.getString("dokter"), rs.getDate("tanggalmasuk"), rs.getDate("tanggalkeluar"), rs.getInt("biaya")));
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
	}
	
	@FXML
	public void Logout(ActionEvent event) throws IOException{
    
        try{    
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource("/application/Tesd.fxml"));
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
        		Pasien selectedItem = tblPasien.getSelectionModel().getSelectedItem();
        		   			    			
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("ubahdata.fxml"));
            	Parent root = (Parent) loader.load();
            	MainController4 secController = loader.getController();
            	secController.setNomor1(selectedItem.getNomor().toString());
            	secController.setNama1(selectedItem.getNama().toString());
            	secController.setKelamin1(selectedItem.getJenisKelamin().toString());
            	secController.setUsia1(selectedItem.getUsia().toString());
            	secController.setRuang1(selectedItem.getRuang().toString());
            	secController.setPenyakit1(selectedItem.getPenyakit().toString());
            	secController.setDokter1(selectedItem.getDokter().toString());
            	secController.setTglMasuk1(selectedItem.getMasuk().toString());
//            	secController.setTglKeluar1(selectedItem.getKeluar().toString());
            	secController.setBiaya1(selectedItem.getBiaya().toString());
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
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource("/application/tambahdata.fxml"));
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
	 ResultSet resultSet = null;
	 
	 @FXML
		public void Cari(ActionEvent event) throws IOException{
	    
		 	try {
			    java.sql.Connection c = (Connection) teskoneksi.configDB();
		    	String sql = "SELECT * FROM pasiendata WHERE nama LIKE '%"+txtCari.getText() +"%'";
		        java.sql.Statement s = c.createStatement();
		        ResultSet rs = s.executeQuery(sql);
		        tblPasien.getItems().clear();
		        while (rs.next()) {
		        	list.add(new Pasien(rs.getInt("nomor"), rs.getString("nama"), rs.getString("jeniskelamin"), rs.getInt("usia"), rs.getString("ruang"),rs.getString("penyakit"),rs.getString("dokter"), rs.getDate("tanggalmasuk"), rs.getDate("tanggalkeluar"), rs.getInt("biaya")));
		        }
	           	
			} catch (SQLException e) {
				// TODO: handle exception
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
			try {
				btnHapus.setDisable(false);
				Pasien selectedItem = tblPasien.getSelectionModel().getSelectedItem();
				java.sql.Connection c = (Connection) teskoneksi.configDB();
			    String hapusSql = "DELETE FROM pasiendata WHERE nomor = ?";
			    stmt = (PreparedStatement) c.prepareStatement(hapusSql);
			    stmt.setInt(1, selectedItem.getNomor());
			    stmt.executeUpdate();
			    	
				tblPasien.getItems().remove(selectedItem);
			} catch (SQLException e) {
				// TODO: handle exception
				 e.printStackTrace();
			}
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
	    	String sql = "SELECT * FROM pasiendata";
	        java.sql.Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        
	        while (rs.next()) {
	        	list.add(new Pasien(rs.getInt("nomor"), rs.getString("nama"), rs.getString("jeniskelamin"), rs.getInt("usia"), rs.getString("ruang"),rs.getString("penyakit"),rs.getString("dokter"), rs.getDate("tanggalmasuk"), rs.getDate("tanggalkeluar"), rs.getInt("biaya")));
	        }
		}
		 catch(Exception e){
	            e.printStackTrace();
	    }
		
		nomor.setCellValueFactory(new PropertyValueFactory<Pasien, Integer>("nomor"));
		nama.setCellValueFactory(new PropertyValueFactory<Pasien, String>("nama"));
		jenisKelamin.setCellValueFactory(new PropertyValueFactory<Pasien, String>("jenisKelamin"));
		usia.setCellValueFactory(new PropertyValueFactory<Pasien, Integer>("usia"));
		ruang.setCellValueFactory(new PropertyValueFactory<Pasien, String>("ruang"));
		penyakit.setCellValueFactory(new PropertyValueFactory<Pasien, String>("penyakit"));
		dokter.setCellValueFactory(new PropertyValueFactory<Pasien, String>("dokter"));
		masuk.setCellValueFactory(new PropertyValueFactory<Pasien, Date>("masuk"));
		keluar.setCellValueFactory(new PropertyValueFactory<Pasien, Date>("keluar"));
		biaya.setCellValueFactory(new PropertyValueFactory<Pasien, Integer>("biaya"));
		tblPasien.setItems(list);
	}
}

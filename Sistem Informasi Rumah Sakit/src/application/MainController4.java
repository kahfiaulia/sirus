package application;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class MainController4 implements Initializable{
	@FXML
	private Spinner<Integer> spnNomor;
	
	@FXML
	private TextField txtNama;
	
	@FXML
	private ChoiceBox<String> cbKelamin;
	
	@FXML
	private Spinner<Integer> spnUsia;
	
	@FXML
	private ChoiceBox<String> cbRuang;
	
	@FXML
	private TextField txtPenyakit;
	
	@FXML
	private ChoiceBox<String> cbDokter;
	
	@FXML
	private DatePicker dpTanggalMasuk;
	
	@FXML
	private DatePicker dpTanggalKeluar;
	
	@FXML
	private Button btnSimpan;
	
	@FXML
	private Button btnTutup;
	
	@FXML
	private TextField txtBiayaPerawatan;
	
	@FXML
	private TextField txtRegistrasiKamar;

	@FXML
	private TextField txtSewaKamar;
	
	@FXML
	private TextField txtDokter;
	
	@FXML
	private TextField txtAmbulans;
	
	@FXML
	private TextField txtPenunjangMedis;
	
	@FXML
	private TextField txtLainlain;
	
	@FXML
	private TextField txtTotalBiaya;
	
	@FXML
	private ObservableList<String> ruangList = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<String> dokterList = FXCollections.observableArrayList();
	
	@FXML
	public void Tutup(ActionEvent event) throws IOException{
    
        try{       	
            	Parent halamanUtamaParent = FXMLLoader.load(getClass().getResource("/application/halamanUtama.fxml"));
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
	
	@FXML
	public void Bayar(ActionEvent event) throws IOException{
		
	    String text1 = txtBiayaPerawatan.getText();
	    String text2 = txtRegistrasiKamar.getText();
	    String text3 = txtSewaKamar.getText();
	    String text4 = txtDokter.getText();
	    String text5 = txtAmbulans.getText();
	    String text6 = txtPenunjangMedis.getText();
	    String text7 = txtLainlain.getText();
	    // parse() returns type int or Integer
	    int int1 = Integer.parseInt(text1);
	    int int2 = Integer.parseInt(text2);
	    int int3 = Integer.parseInt(text3);
	    int int4 = Integer.parseInt(text4);
	    int int5 = Integer.parseInt(text5);
	    int int6 = Integer.parseInt(text6);
	    int int7 = Integer.parseInt(text7);
	    
	    // using Integer here so we can use toString() next
	    Integer result = int1 + int2 + int3 + int4 + int5 + int6 + int7;
	    // setText() takes input of type String
	    txtTotalBiaya.setText(result.toString());
	}
	
	 Connection connection = null;
	 PreparedStatement  stmt = null;
	
	 	public void setNomor1 (Object object) {
			spnNomor.getEditor().setText((String) object);
		}
		
		public void setNama1 (Object object) {
			txtNama.setText((String) object);
		}
		
		public void setKelamin1 (Object object) {
			cbKelamin.setValue((String) object);
		}
		
		public void setUsia1 (Object object) {
			spnUsia.getEditor().setText((String) object);
		}
		
		public void setRuang1 (Object object) {
			cbRuang.setValue((String) object);
		}
		
		public void setPenyakit1 (Object object) {
			txtPenyakit.setText((String) object);
		}
		
		public void setDokter1 (Object object) {
			cbDokter.setValue((String) object);
		}
		
		public void setTglMasuk1 (Object object) {
			dpTanggalMasuk.getEditor().setText((String) object);
		}
		
//		public void setTglKeluar1 (Object object) {
//			dpTanggalKeluar.getEditor().setText((String) object);
//		}
		
		public void setBiaya1 (Object object) {
			txtTotalBiaya.setText((String) object);
		}
		
	@FXML
	public void Simpan(ActionEvent event) throws IOException, SQLException{
		java.sql.Connection con = (Connection) teskoneksi.configDB();
    	String sql = "UPDATE pasiendata SET nomor= ?, nama = ?, jeniskelamin = ?, usia = ?, ruang = ?, penyakit= ?,  dokter = ?, tanggalmasuk = ?, tanggalkeluar = ?, biaya = ? WHERE nomor= '"+spnNomor.getEditor().getText()+"'";
    	stmt = (PreparedStatement) con.prepareStatement(sql);
    	stmt.setString(1, ((TextField)spnNomor.getEditor()).getText());
    	stmt.setString(2, txtNama.getText());
    	stmt.setString(3, cbKelamin.getValue());
    	stmt.setString(4, ((TextField)spnUsia.getEditor()).getText());
    	stmt.setString(5, cbRuang.getValue());
    	stmt.setString(6, txtPenyakit.getText());
    	stmt.setString(7, cbDokter.getValue());
    	stmt.setString(8, dpTanggalMasuk.getValue().toString());
    	stmt.setString(9, dpTanggalKeluar.getValue().toString());
    	stmt.setString(10, txtTotalBiaya.getText());
    	stmt.executeUpdate();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Berhasil");
    	alert.setHeaderText(null);
    	alert.setContentText("Data telah berhasil diubah.");

    	alert.showAndWait();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
		spnNomor.setValueFactory(valueFactory);
		SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
		spnUsia.setValueFactory(valueFactory1);
		cbKelamin.getItems().add("Laki-Laki");
		cbKelamin.getItems().add("Perempuan");
		
		String ruangNama = " SELECT * from ruangdata ";

	    try {
	    	java.sql.Connection c = (Connection) teskoneksi.configDB();
	        PreparedStatement pstStn = (PreparedStatement) c.prepareStatement(ruangNama);
	        ResultSet stnRS = pstStn.executeQuery(ruangNama);

	        while (stnRS.next()) {
	            ruangList.add(stnRS.getString("ruang"));
	            cbRuang.setItems(ruangList);
	        }

	        stnRS.close();
	        pstStn.close();
	        c.close();

	    } catch (SQLException ex) {
	        System.err.println("ERR" + ex);
	    }
		
		String dokterNama = " SELECT * from dokterdata ";

	    try {
	    	java.sql.Connection c = (Connection) teskoneksi.configDB();
	        PreparedStatement pstStn = (PreparedStatement) c.prepareStatement(dokterNama);
	        ResultSet stnRS = pstStn.executeQuery(dokterNama);

	        while (stnRS.next()) {
	            dokterList.add(stnRS.getString("nama"));
	            cbDokter.setItems(dokterList);
	        }

	        stnRS.close();
	        pstStn.close();
	        c.close();

	    } catch (SQLException ex) {
	        System.err.println("ERR" + ex);
	    }
		dpTanggalMasuk.setConverter(new StringConverter<LocalDate>() {
		     String pattern = "yyyy-MM-dd";
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
		
		dpTanggalKeluar.setConverter(new StringConverter<LocalDate>() {
		     String pattern = "yyyy-MM-dd";
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
	}
	
}


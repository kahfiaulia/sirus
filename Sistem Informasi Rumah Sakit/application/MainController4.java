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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	
	 Connection connection = null;
	 PreparedStatement  stmt = null;	
	 
	@FXML
	public void Simpan(ActionEvent event) throws IOException, SQLException{
		java.sql.Connection con = (Connection) teskoneksi.configDB();
    	String sql = "UPDATE pasiendata SET nomor= ?, nama = ?, jeniskelamin = ?, usia = ?, ruang = ?, penyakit= ?,  dokter = ?, tanggalmasuk = ? WHERE nama = '"+txtNama.getText()+"'";
    	stmt = (PreparedStatement) con.prepareStatement(sql);
    	stmt.setString(1, ((TextField)spnNomor.getEditor()).getText());
    	stmt.setString(2, txtNama.getText());
    	stmt.setString(3, cbKelamin.getValue());
    	stmt.setString(4, ((TextField)spnUsia.getEditor()).getText());
    	stmt.setString(5, cbRuang.getValue());
    	stmt.setString(6, txtPenyakit.getText());
    	stmt.setString(7, cbDokter.getValue());
    	stmt.setString(8, dpTanggalMasuk.getValue().toString());
    	stmt.executeUpdate();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection c = (Connection) teskoneksi.configDB();
			String sql = "SELECT * FROM pasiendata WHERE nomor = '"+spnNomor.getValue() +"' AND nama = '"+txtNama.getText()+"'";
        	stmt = (PreparedStatement) c.prepareStatement(sql);
    		stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
		spnNomor.setValueFactory(valueFactory);
		SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
		spnUsia.setValueFactory(valueFactory1);
		cbKelamin.getItems().add("Laki-Laki");
		cbKelamin.getItems().add("Perempuan");
		
		cbRuang.getItems().add("Melati");
		cbRuang.getItems().add("Mawar");
		cbRuang.getItems().add("Anggrek");
		
		cbDokter.getItems().add("dr. Fahrudin");
		cbDokter.getItems().add("dr. Adi");
		cbDokter.getItems().add("dr. Nirmala");
	}
	
	
}


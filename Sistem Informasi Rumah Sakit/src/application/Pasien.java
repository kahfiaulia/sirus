package application;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pasien {
    private final SimpleIntegerProperty nomor;
    private final SimpleStringProperty nama;
    private final SimpleStringProperty jenisKelamin;
    private final SimpleIntegerProperty usia;
    private final SimpleStringProperty ruang;
    private final SimpleStringProperty penyakit;
    private final SimpleStringProperty dokter;
    private final SimpleObjectProperty < Date > masuk;
    private final SimpleObjectProperty < Date > keluar;
    private final SimpleIntegerProperty biaya;

    public Pasien(Integer nomor, String nama, String jenisKelamin, 
    		Integer usia, String ruang, String penyakit, String dokter, Date masuk, Date keluar, Integer biaya) {
    	super();
        this.nomor = new SimpleIntegerProperty(nomor);
        this.nama = new SimpleStringProperty(nama);
        this.jenisKelamin = new SimpleStringProperty(jenisKelamin);
        this.usia = new SimpleIntegerProperty(usia);
        this.ruang = new SimpleStringProperty(ruang);
        this.penyakit = new SimpleStringProperty(penyakit);
        this.dokter = new SimpleStringProperty(dokter);
        this.masuk = new SimpleObjectProperty < Date > (masuk);
        this.keluar = new SimpleObjectProperty < Date > (keluar);
        this.biaya = new SimpleIntegerProperty(biaya);
    }
    
    public Integer getNomor() {
        return nomor.get();
    }
    public String getNama() {
        return nama.get();
    }
    public String getJenisKelamin() {
        return jenisKelamin.get();
    }
    public Integer getUsia() {
        return usia.get();
    }
    public String getRuang() {
        return ruang.get();
    }
    public String getPenyakit() {
        return penyakit.get();
    }
    public String getDokter() {
        return dokter.get();
    }
    public Date getMasuk() {
        return masuk.get();
    }
    public Date getKeluar() {
        return keluar.get();
    }
    public Integer getBiaya() {
        return biaya.get();
    }
}


package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Dokter {
    private final SimpleIntegerProperty nomor;
    private final SimpleIntegerProperty level;
    private final SimpleStringProperty nama;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public Dokter(Integer nomor, Integer level, String nama, String username, String password) {
    	super();
        this.nomor = new SimpleIntegerProperty(nomor);
        this.level = new SimpleIntegerProperty(level);
        this.nama = new SimpleStringProperty(nama);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }
    
    public Integer getNomor() {
        return nomor.get();
    }
    
    public Integer getLevel() {
        return level.get();
    }
    public String getNama() {
        return nama.get();
    }
    public String getUsername() {
        return username.get();
    }
    public String getPassword() {
        return password.get();
    }
}


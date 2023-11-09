package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ruang {
    private final SimpleIntegerProperty nomor;
    private final SimpleStringProperty ruang;

    public Ruang(Integer nomor, String ruang) {
    	super();
        this.nomor = new SimpleIntegerProperty(nomor);
        this.ruang = new SimpleStringProperty(ruang);
    }
    
    public Integer getNomor() {
        return nomor.get();
    }
    
    public String getRuang() {
        return ruang.get();
    }

}


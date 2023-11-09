package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class teskoneksi {
    
    private static Connection mysqlconfig;
    public static Connection configDB()throws SQLException{
        try {
            String url; 
            url = "jdbc:mysql://localhost:3306/pasien?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
            String user="root"; //user database
            String pass=""; //password database
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);            
        } catch (Exception e) {
            System.err.println("koneksi gagal "+e.getMessage()); //perintah menampilkan error pada koneksi
        }
        return mysqlconfig;
    }    
}
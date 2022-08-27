package storage;

import prefs.Prefs;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {

    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("org.h2.Driver");
                con=DriverManager.getConnection(Prefs.URL);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

}
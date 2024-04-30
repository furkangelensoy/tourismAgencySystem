package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// The database connection is made here.
public class Db {
    private Connection connection = null;
    private static Db instance = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourism_agency";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "123456";

    private Db(){
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public static Connection getInstance(){
        try {
            if (instance == null || instance.getConnection().isClosed()){
                instance = new Db();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}

package com.vdoichev.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/calculator?user=root&password=siavaismail";
            return DriverManager.getConnection(url);
        }catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            ex.printStackTrace();
        }

        return null;
    }
}

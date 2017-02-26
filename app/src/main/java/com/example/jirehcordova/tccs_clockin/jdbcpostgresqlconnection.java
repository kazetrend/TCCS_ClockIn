package com.example.jirehcordova.tccs_clockin;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jireh Cordova on 11/02/2017.
 */

public class jdbcpostgresqlconnection {
    static String chainConnect = "jdbc:postgresql://localhost:5432" + "user=postgres&password=123";
    static String requestSQL = "empty";


    private static final String DB_DRIVER = "org.postgresql.Driver";
    public static void main(String[] args){
        final String Tag = "JdbcPostgresqlConnection";

        Connection conn1 = null;
        Connection conn2 = null;
        Connection conn3 = null;

        try {
            // connect method 1
            String dbURL = "jdbc:postgresql:try?user=postgres&password=123";
            conn1 = DriverManager.getConnection(dbURL);
            if (conn1 != null) {
                //"connected to database 1");
                System.out.println("Connection Success");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}

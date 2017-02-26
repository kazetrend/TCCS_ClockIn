package com.example.jirehcordova.tccs_clockin;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * Created by Jireh Cordova on 11/02/2017.
 */

public class postgreshelper extends AsyncTask<Void, Void, String>{
    static String chainconn = "jdbc:postgresql://localhost:5432/try" + "user=postgres&password=123";
   String retval="";
    @Override
    protected String doInBackground(Void... params) {
        String url = "jdbc:postgresql://192.168.1.8:5432/tccs?user=postgres&password=InnaGwapa11790";
        Connection conn;
        /*try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }*/
        //try{
        //DriverManager.registerDriver(new org.postgresql.Driver());
        try {
            conn = DriverManager.getConnection(url);


            Statement st = conn.createStatement();
            String sql;
            sql = "SELECT * from mobile_user";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                retval = rs.getString(1);
            }
            Log.d("Letmesee", "barara");
            rs.close();
            st.close();
            conn.close();
       }catch (SQLException e) {
            e.printStackTrace();
            retval = e.toString();
        }
        Log.d("retval", retval);
        return retval;
    }
    @Override
    protected void onPostExecute(String value) {
        Log.d("showconnect", value);
    }

}

/*public class postgreshelper extends AsyncTask <Void, Void, Void> {
    static String chainconn = "jdbc:postgresql://localhost:5432/try?" + "user=postgres&password=123";
    static String responseSQL = "empty";
   // private Connection conn;
    private String host;
    private String dbName;
    private String user;
    private String pass;
    Statement st = null;
    ResultSet rs = null;
    Connection connect;

    //I really needed this constructor T_T
    protected postgreshelper(){}

    @Override
    protected Void doInBackground(Void... params) {
        //Connection connect = null;
        try{
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(chainconn);
            responseSQL = "";
        } catch (Exception e) {
            e.printStackTrace();
            //connection = null;
        }
        return null;
    }

    public postgreshelper(String host, String dbName, String user, String pass){
        this.host = host;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

*//*    public boolean connect() throws SQLException, ClassNotFoundException{
        if(host.isEmpty()||dbName.isEmpty()||user.isEmpty()||pass.isEmpty()){
            throw new SQLException("Database misssing some creds");
        }
        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(this.host + this.dbName, this.user, this.pass);
        return true;
    }*//*

    public int insert(Map values) throws SQLException {

        StringBuilder columns = new StringBuilder();
        StringBuilder vals = new StringBuilder();

        //Connection conn = DriverManager.getConnection(this.host + this.dbName, this.user, this.pass);
        Log.d("i", chainconn);
        Log.d("i1", dbName);
        Log.d("i2", user);
        Log.d("i3", pass);

        for (Object col : values.keySet()) {
            columns.append(col).append(",");
            if (values.get(col) instanceof String) {
                vals.append("'").append(values.get(col)).append("',");
            } else vals.append(values.get(col)).append(",");
        }

        columns.setLength(columns.length() - 1);
        vals.setLength(vals.length() - 1);

        st = connect.createStatement();
        String querySQL = "INSERT INTO userinfo (username, email) VALUES ('kai','kai@gmail.com')";
        rs = st.executeQuery(querySQL);

        *//*String query = String.format("INSERT INTO %s (%s) VALUES (%s)", table, columns.toString(), vals.toString());
        Log.d("insertintodb", query);
        //PreparedStatement ps = null;
       // try {
          PreparedStatement  ps = chainconn.prepareStatement("INSERT INTO userinfo (email,username) VALUES (?,?);");
        Log.d("insertintodb2", valueOf(ps));
            ps.setString(1, "test@test.com");
            ps.setString(2, "test");
            int rows = ps.executeUpdate();
        Log.d("insertintodb3", valueOf(ps));*//*

        return 1;
    }

}*/


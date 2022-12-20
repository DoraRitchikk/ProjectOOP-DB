package com.ritchik.Myevents.helpers.GlobalDB;

import android.os.StrictMode;
import android.util.Log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class GlobalDBHelper {

    private static final String SERVER_IP = "192.168.188.18";
    private static final String SERVER_PORT = "1433";
    private static final String DATABASE_NAME = "MyEvents";
    private static final String SERVER_USERNAME = "test";
    private static final String SERVER_PASSWORD = "test";
    private static final String SERVER_URL = "jdbc:jtds:sqlserver://" + SERVER_IP + ":" + SERVER_PORT + "/" + DATABASE_NAME;
    private Connection connection = null;


    private Connection getConnection() {
        if (connection != null) return connection;
        else {
            try {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
                connection = DriverManager.getConnection(SERVER_URL, SERVER_USERNAME, SERVER_PASSWORD);
                return connection;
            } catch (Exception e) {
                Log.d("ERROR server connection", e.getMessage());
                return null;
            }
        }
    }

    public boolean batchDataAdding() {
        if (getConnection() == null) return false;
        try {
            String query = "" +
                    "insert T1 (FIRSTNAME, LASTNAME, BIRTHPLACE) values ('Daria', 'Ritchik', 'Belarus, Minsk');";

            getConnection().createStatement().executeUpdate(query);
            return true;
        } catch (Exception e ) {
            Log.d("ERROR data adding", e.getMessage());
            return false;
        }
    }

    public String select(String query) {
        if (getConnection() == null) return null;
        try {
            String result = "Result set:\n";
            ResultSet resultSet = getConnection().createStatement().executeQuery(query);

            while (resultSet.next())
                result += "\t" + resultSet.getString(1) +
                        " " + resultSet.getString(2) +
                        " - " + resultSet.getString(3) + "\n";
            resultSet.close();

            return result;
        } catch (Exception e) {
            Log.d("ERROR select", e.getMessage());
            return null;
        }
    }

    public String preperSelect(String firstName1, String firstName2) {
        if (getConnection() == null) return null;
        try {
            String result = "Result set:\n";

            String sql = "select FIRSTNAME, LASTNAME, BIRTHPLACE from T1 where FIRSTNAME = 'Daria' ;";

            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                result += "\t" + resultSet.getString(1) +
                        " " + resultSet.getString(2) +
                        " - " + resultSet.getString(3) + "\n";
            resultSet.close();

            return result;
        } catch (Exception e) {
            Log.d("ERROR select", e.getMessage());
            return null;
        }
    }

    public boolean update() {
        if (getConnection() == null) return false;
        try {
            String query = "" +
                    "update T1 set FIRSTNAME = 'Andrey', LASTNAME = 'Samoyluk' where LASTNAME = 'Pinchuk' ";

            getConnection().createStatement().executeUpdate(query);
            return true;
        } catch (Exception e ) {
            Log.d("ERROR update data", e.getMessage());
            return false;
        }
    }

    public boolean delete() {
        if (getConnection() == null) return false;
        try {
            getConnection().createStatement().executeUpdate("delete from T1");
            return true;
        } catch (Exception e ) {
            Log.d("ERROR delete data", e.getMessage());
            return false;
        }
    }


    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            Log.d("ERROR close connection", e.getMessage());
        }
    }
}

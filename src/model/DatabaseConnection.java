package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Anders on 4/21/2016.
 */
public class DatabaseConnection {

    /**
     * Create connection shortly to get slide information and shut down afterwards.
     * Works both locally and on the Raspberry pi if these configurations are used.
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root", "root");

        } catch(Exception e){
            e.printStackTrace();
        }

        return connection;

    }


}

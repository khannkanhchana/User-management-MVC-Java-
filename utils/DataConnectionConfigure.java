package MVC.studentmanagement.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnectionConfigure {
    private static String username = "postgres";
    private static String password = "111999";
    private static String url = "jdbc:postgresql://localhost:5432/user_db";
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    url,
                    username,
                    password
            );

        }catch (Exception exception){
            System.out.println("Error during Establishing connection database ");
        }
        return null;
    }
}

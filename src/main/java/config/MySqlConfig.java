package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConfig {
    private static final String url = "jdbc:mysql://localhost:3308/crm_app";
    private static final String username = "root";
    private static final String password = "admin123";

    public static Connection getConnection() {
        Connection connection = null;

        try{
            //Chỉ định drive sử dụng
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Tạo kết nối đến csdl
            connection =  DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.out.println("Lỗi kết nối tới cơ sỡ dữ liệu: " + e.getMessage());
        }

        return connection;
    }
}

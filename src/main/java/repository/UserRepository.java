package repository;

import config.MySqlConfig;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<UserModel> findByEmailAndPassword(String email, String password) {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try{
            String sql = "select * from users u where u.email = ? and u.password = ?";

            connection = MySqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                UserModel userModel = new UserModel();
                // lấy giá trị cột chỉ định và lưu vào object
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullName"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error findByEmailAndPassword " + e.getMessage());
        }finally {
            if (connection != null ){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối login: " + e.getMessage());
                }
            }
        }
        return userModelList;
    }

    public List<UserModel> findAll(){
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try{
            String sql = "select * from users u";

            connection = MySqlConfig.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                UserModel userModel = new UserModel();
                // lấy giá trị cột chỉ định và lưu vào object
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullName"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error findAll " + e.getMessage());
        }finally {
            if (connection != null ){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findAll: " + e.getMessage());
                }
            }
        }
        return userModelList;
    }

    public boolean insertUser(String fullName, String email, String password, int roleId) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MySqlConfig.getConnection();

            String sql = "INSERT INTO users(email,password,fullname,role_id) values (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3,fullName);
            statement.setInt(4, roleId);

            int count = statement.executeUpdate();
            isSuccess = count > 0;

        }catch (Exception e){
            System.out.println("Error Query insertUser " + e.getMessage());
        }finally {
            if (connection != null ){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối InsertUser: " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}
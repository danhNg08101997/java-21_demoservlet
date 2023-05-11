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

    // Insert User
    public void insertUser(String fullName, String email, String password, int roleId) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MySqlConfig.getConnection();

            String sql = "insert into users(email,password,fullname,role_id) values (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3,fullName);
            statement.setInt(4, roleId);

            int count = statement.executeUpdate();
            isSuccess = count > 0;

        }catch (Exception e){
            System.out.println("Error Query InsertUser " + e.getMessage());
        }finally {
            if (connection != null ){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối InsertUser: " + e.getMessage());
                }
            }
        }
    }

    // Delete User
    public boolean deleteById(int id) {
        Connection connection = null;
        boolean isSuccess = false;

        try{
            connection = MySqlConfig.getConnection();

            String sql = "delete from users u where u.id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int count = statement.executeUpdate();
            isSuccess = count > 0;

        }catch (Exception e){
            System.out.println("Error Query DeleteById: " + e.getMessage());

        }finally {
            if (connection != null) {
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối DeleteById: " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    // Find by ID
    public UserModel findById(int id){
        Connection connection = null;
        UserModel userModel = new UserModel();

        try{
            String sql = "select * from users u where u.id = ?";

            connection = MySqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                // lấy giá trị cột chỉ định và lưu vào object
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullName"));
                userModel.setRoleId(resultSet.getInt("role_id"));

            }
        }catch (Exception e){
            System.out.println("Error findById " + e.getMessage());
        }finally {
            if (connection != null ){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findById: " + e.getMessage());
                }
            }
        }
        return userModel;
    }


    // Update User
    public void updateById(UserModel userModel) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MySqlConfig.getConnection();

            String sql = "update users u set email = ?, fullname = ?, role_id = ? where id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userModel.getEmail());
            statement.setString(2,userModel.getFullname());
            statement.setInt(3, userModel.getRoleId());
            statement.setInt(4, userModel.getId());

            int count = statement.executeUpdate();
            isSuccess = count > 0;

        }catch (Exception e){
            System.out.println("Error Query UpdateById " + e.getMessage());
        }finally {
            if (connection != null ){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối UpdateById: " + e.getMessage());
                }
            }
        }
//        return isSuccess;
    }
}
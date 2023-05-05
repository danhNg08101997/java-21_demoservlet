package repository;

import config.MySqlConfig;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> findAllRoles () {
        Connection connection = null;
        List<RoleModel> roleModelList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM roles r";

            connection = MySqlConfig.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RoleModel roleModel = new RoleModel();

                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));

                roleModelList.add(roleModel);
            }
        }catch (Exception e) {
            System.out.println("Error findAllRoles " + e.getMessage());
        }finally {
            if (connection != null ){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findAllRoles: " + e.getMessage());
                }
            }
        }
        return roleModelList;
    }
}

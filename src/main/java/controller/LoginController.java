package controller;


import config.MySqlConfig;
import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Connection connection = null;
        // Bước 1: Lấy tham số email và password người dùng nhập bên form
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Bước 2: viết câu query
        String sql = "select * from users u where u.email = ? and u.password = ?";

        //Bước 3: đứa câu query vào statement để thực thi
        try {
            connection = MySqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            // Truyền tham số cho dấu ? trong câu query
            statement.setString(1, email);
            statement.setString(2, password);

            // Bước 4: thực thi câu query
            // statement có 2 loại thực thi
            // excuteQuery: khi câu query là câu select;
            // excuteUpdate: insert, update, delete,...;
           ResultSet resultSet = statement.executeQuery();
            List<UserModel> list = new ArrayList<>();

            // Bước 5 duyệt dữ liệu trong ResultSet và lưu vào trong list userModel
            while(resultSet.next()){
                UserModel userModel = new UserModel();
                // lấy giá trị cột chỉ định và lưu vào object
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullName"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                list.add(userModel);
            }
            boolean isSuccess = list.size() > 0;
            PrintWriter writer = resp.getWriter();
            writer.println(isSuccess?"Login Success":"Login Fail");
            writer.close();
            System.out.println("Kiểm tra: " + list.size());
        } catch (Exception e){
            System.out.println("Lỗi thực thi query login: " + e.getMessage());
        }finally {
            if (connection != null ){
               try{
                   connection.close();
               }catch (Exception e){
                   System.out.println("Lỗi đóng kết nối login: " + e.getMessage());
               }

            }
        }

    }
}

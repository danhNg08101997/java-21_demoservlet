package controller;


import config.MySqlConfig;
import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
//        Cách khởi tạo cookie
//         Cookie cookie = new Cookie("email", "nguyenvana");
//        Yêu cầu client khởi tạo cookie
//         resp.addCookie(cookie);

//        Cookie[] cookies = req.getCookies();
//        for(Cookie item : cookies) {
//            if(item.getName().equals("email")){
//                System.out.println("Kiểm tra cookie: " + item.getValue());
//            }
//        }

//      Khởi tạo session
//        HttpSession session = req.getSession();
//        session.setAttribute("password", "123456");
//        System.out.println("Session: " + session.getAttribute("password"));

        /*
        * Tạo ra checkbox nhớ mật khẩu, khi người dùng click chọn checkbox
        * thì khi đăng nhập thành công sẽ lưu lại giá trị đăng nhập là email, pass
        *
        * Khi quay lại màn hình login thì tự động điền email và pass
        * */
//        Ghi nhớ mật khẩu
        Cookie[] cookies = req.getCookies();
        String email = "";
        String password = "";
        for(Cookie item:cookies) {
            if (item.getName().equals("email")){
                email = item.getValue();
            }
            if (item.getName().equals("password")) {
                password = item.getValue();
            }
        }
//        Trả giá trị ra màn hình
        req.setAttribute("email", email);
        req.setAttribute("password", password);

        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Connection connection = null;
        // Bước 1: Lấy tham số email và password người dùng nhập bên form
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        // ghi nhớ mật khẩu
        String rememberAcc = (String) req.getParameter("checkboxLoginName");

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
//            Ghi nhớ mật khẩu
            if(isSuccess && rememberAcc != null){
//            Lưu cookie
                Cookie cEmail = new Cookie("email", email);
                Cookie cPassword = new Cookie("password", password);
                resp.addCookie(cEmail);
                resp.addCookie(cPassword);
            }

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

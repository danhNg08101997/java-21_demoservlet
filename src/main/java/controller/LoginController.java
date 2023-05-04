package controller;


import config.MySqlConfig;
import model.UserModel;
import service.LoginService;

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

    private LoginService loginService = new LoginService();

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
        for (Cookie item : cookies) {
            if (item.getName().equals("email")) {
                email = item.getValue();
            }
            if (item.getName().equals("password")) {
                password = item.getValue();
            }
        }
//        Trả giá trị ra màn hình
        req.setAttribute("email", email);
        req.setAttribute("password", password);

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberAcc = (String) req.getParameter("checkboxLoginName");

        boolean isSuccess = loginService.checkLogin(email, password);
        if (isSuccess) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/dashboard");
        } else {
            PrintWriter writer = resp.getWriter();
            writer.println("Login Fail!");
            writer.close();
        }
    }
}

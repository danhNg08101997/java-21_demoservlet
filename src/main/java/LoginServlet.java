//import model.Users;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class LoginServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        resp.setContentType("text/html;charset=UTF-8");
////        System.out.println("login page");
////        PrintWriter writer = resp.getWriter();
////        writer.println("Hello Login Page");
//
//
////        int a = 4;
////        int b = 5;
////        int result = a + b;
////        req.setAttribute("kq",result);   // servlet truyền biến ra cho file jsp
//
//
//
//// Yêu cầu sử dụng giao diện và trả giao diện ra browser cho client
//        req.getRequestDispatcher("login.jsp").forward(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//
//
//        String message = "";
//        System.out.println("kiểm tra: " + email);
//
//        if(email.equalsIgnoreCase("danh@gmail.com")){
//            message = "Hello " + email;
//        }
//        req.setAttribute("msg", message);
//
//        Users users = new Users();
//        users.setUsername(email);
//        users.setPassword(password);

//List<String> list = Array.asList("Cybersoft", "java 21", "JSP");
//req.setAttribute("list",list);

//
//        req.setAttribute("users", users);
//
//
//// Yêu cầu sử dụng giao diện và trả giao diện ra browser cho client
//        req.getRequestDispatcher("login.jsp").forward(req,resp);
//    }
//}

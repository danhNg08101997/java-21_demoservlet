package controller;

import model.RoleModel;
import model.UserModel;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user/add", "/user/delete", "/user/edit"})
public class UserController extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        // Lấy đường dẫn servlet người dùng gọi trên browser
        switch (path) {
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            case "/user/edit":
                getUser(req, resp);
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        // Lấy đường dẫn servlet người dùng gọi trên browser
        switch (path) {
            case "/user":
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/edit":
                editUser(req, resp);
                break;

            default:
                break;
        }
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userService.getAllUser();
        req.setAttribute("listUser", list);
        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletMethod = req.getMethod();
        List<RoleModel> listRoles = userService.getAllRoles();

        if (servletMethod.equalsIgnoreCase("post")){
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int roleId = Integer.parseInt(req.getParameter("role"));

            userService.insertUser(fullName, email, password, roleId);
        }

        req.setAttribute("listRoles", listRoles);
        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = userService.deleteUser(id);
    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        UserModel userModel = userService.getUser(id);
        List<RoleModel> listRoles = userService.getAllRoles();

        req.setAttribute("email", userModel.getEmail());
        req.setAttribute("fullName", userModel.getFullname());
        req.setAttribute("roldId", userModel.getRoleId());
        req.setAttribute("id", userModel.getId());

        req.setAttribute("listRoles", listRoles);

        req.getRequestDispatcher("/user-edit.jsp").forward(req,resp);
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel userModel = new UserModel();
        userModel.setEmail(req.getParameter("email"));
        userModel.setFullname(req.getParameter("fullName"));
        userModel.setRoleId(Integer.parseInt(req.getParameter("role")));

        userService.updateUser(userModel);

        req.getRequestDispatcher("/user-edit.jsp").forward(req,resp);
    }
}

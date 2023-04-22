
// Annotation: @

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "annotationServlet", urlPatterns = {"/demoannotation"})
public class AnnotationServlet extends HttpServlet {

    // GET: Mặc định khi gọi đường dẫn. Không bảo mật tham số mà client truyền lên.
    //      Cách truyền tham số: ?tenthamso=giatri&tenthamso=giatri
    //      Có giới hạn về tham số gọi trên trình duyệt

    // POST: Tham số sẽ được truyền ngầm. Tham số sẽ được bảo mật
    //      Tham số sẽ được truyền thông qua code và thẻ form trong HTML
    //      Không có giới hạn về tham số

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
       String username =  req.getParameter("username");
       String password = req.getParameter("password");

        PrintWriter writer = resp.getWriter();
//        writer.println("Demo Annotation");
        writer.println("Hello " + username + " có password là: " + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello doPost");
    }
}

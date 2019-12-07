package cn.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 少杰
 * @create 2019/3/21  14:48
 * GrowingUp  cn.servlet
 */
@WebServlet(name = "AdminPage" ,urlPatterns = "/admin")
public class AdminPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 管理员请求登录时     跳转管理员登录页面
        resp.sendRedirect("admin/adminLogin.html");
    }
}

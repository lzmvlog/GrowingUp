package cn.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 少杰
 * @create 2019/4/18  16:08
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "UserVerificationPassWord",urlPatterns = "/verification")
public class UserVerificationPassWord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取 session 中的验证码
        int number = (int)req.getSession().getAttribute("number");
        int updateEmail = Integer.parseInt(req.getParameter("verificationNumber"));
        PrintWriter writer = resp.getWriter();
        if (number == updateEmail){
            writer.println(true);
        }else{
            writer.println(false);
        }
    }
}

package cn.servlet.admin;

import cn.mapper.AdminMapper;
import cn.model.Admin;
import cn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 少杰
 * @create 2019/4/3  16:40
 * GrowingUp  cn.servlet.admin
 */
@WebServlet(name = "AdminLogin" ,urlPatterns = "/AdminLogin")
public class AdminLogin extends HttpServlet {
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
        // 获取管理员的账号 密码信息
        String account =  req.getParameter("account");
        String password = req.getParameter("password");
        Admin admin = new Admin();
        admin.setAccount(account);
        admin.setPassword(password);
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PrintWriter writer = resp.getWriter();
        try {
            AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
            Integer i = mapper.login(admin);
            if(i > 0){
                writer.println("admin");
            }else{
                resp.sendRedirect("adminLogin.html");
            }
        }finally{
            sqlSession.close();
            writer.flush();
            writer.close();
        }

    }
}

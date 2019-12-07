package cn.servlet.user;

import cn.mapper.UserMapper;
import cn.model.User;
import cn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 少杰
 * @create 2019/3/21  15:21
 * GrowingUp  cn.servlet
 */
@WebServlet(name = "UserLogin" ,urlPatterns = "/userLogin")
public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取数据
        String userEmail = req.getParameter("email_login");
        String password = req.getParameter("password_login");
        // 封装数据
        User user = new User();
        user.setEmail(userEmail);
        user.setPassword(password);
        // 处理sql
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PrintWriter writer = resp.getWriter();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Integer i = mapper.userLogin(user);
            System.out.println();
            if (i > 0){
                HttpSession session = req.getSession();
                session.setAttribute("email",user.getEmail());
                writer.println(true);
            }else{
                writer.println(false);
            }
        }finally{
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

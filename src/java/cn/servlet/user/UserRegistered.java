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
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 少杰
 * @create 2019/3/27  16:09
 * GrowingUp  cn.servlet
 */
@WebServlet(name = "UserRegistered" ,urlPatterns = "/registered")
public class UserRegistered extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取信息
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        // 实例化用户对象
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        // 处理sql
        System.out.println(name + email + password);
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PrintWriter writer = resp.getWriter();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Integer i = mapper.userRegistered(user);
            if(i > 0 ){
                sqlSession.commit();
                writer.println(true);
            }else{
                writer.println(false);
            }
        }catch (Exception e){
            sqlSession.rollback();
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

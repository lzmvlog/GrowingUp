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
 * @create 2019/4/23  10:13
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "UserUpdatePassWord",urlPatterns = "/update")
public class UserUpdatePassWord extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取用户的 email 用于修改用户的账号密码
        String email = req.getParameter("email");
        // 获取账号
        String password = req.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            // 处理 SQL
            Integer i = mapper.updateUserPassWord(user);
            sqlSession.commit();
            if (i > 0){
                writer.println(true);
            }else{
                writer.println(false);
            }
        }catch (Exception e){
            // 数据回滚
            sqlSession.rollback();
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

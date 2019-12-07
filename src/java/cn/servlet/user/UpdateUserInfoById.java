package cn.servlet.user;

import cn.mapper.UserMapper;
import cn.model.User;
import cn.util.SqlSessionFactoryUtil;
import com.alibaba.fastjson.JSON;
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
 * @create 2019/5/6  23:33
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "UpdateUserInfoById",urlPatterns = "/admin/updateUserInfoById")
public class UpdateUserInfoById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取数据
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            Integer i = mapper.updateUserPassWord(user);
            if (i > 0){
                sqlSession.commit();
                writer.println(true);
            }
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

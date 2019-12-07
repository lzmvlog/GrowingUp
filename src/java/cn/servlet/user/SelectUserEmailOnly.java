package cn.servlet.user;

import cn.mapper.UserMapper;
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
 * @create 2019/4/26  10:44
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "SelectUserEmailOnly",urlPatterns = "/selectUserEmailOnly")
public class SelectUserEmailOnly extends HttpServlet {
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
        // 获取数据
        String email = req.getParameter("email");
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            Integer i = mapper.selectUserEmailOnly(email);
            if (i > 0){
                writer.println(true);
            }else{
                writer.println(false);
            }
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

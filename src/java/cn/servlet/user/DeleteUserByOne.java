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
 * @create 2019/5/10  10:24
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "DeleteUserByOne", urlPatterns = "/admin/deleteUserByOne")
public class DeleteUserByOne extends HttpServlet {
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
        Integer id = Integer.parseInt(req.getParameter("userId"));
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            Integer i = mapper.deleteUserByOne(id);
            if (i > 0){
                sqlSession.commit();
                writer.print(true);
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
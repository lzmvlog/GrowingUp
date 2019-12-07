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
 * @create 2019/5/7  11:22
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "SelectUserInfoById",urlPatterns = "/admin/selectUserInfoById")
public class SelectUserInfoById extends HttpServlet {
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
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            User user = mapper.selectUserInfoById(id);
            String userJson = JSON.toJSONString(user);
            writer.println(userJson);
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

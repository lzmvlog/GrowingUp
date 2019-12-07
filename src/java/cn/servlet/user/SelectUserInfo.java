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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 少杰
 * @create 2019/4/9  11:51
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "SelectUserInfo", urlPatterns = "/selectUserInfo")
public class SelectUserInfo extends HttpServlet {
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
        // 获取用户的注册邮箱  email
        String email = (String) req.getSession().getAttribute("email");
        PrintWriter writer = resp.getWriter();
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectUserNameFromUserEmail(email);
            // 创建 JSON
            String userJson = JSON.toJSONString(user);
            if (userJson == null || userJson.equals("") || userJson.equals("null")) {
                writer.println("undefined");
            } else{
                writer.println(userJson);
            }
        } finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

package cn.servlet.post;

import cn.mapper.PostMapper;
import cn.mapper.UserMapper;
import cn.model.Post;
import cn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author 少杰
 * @create 2019/5/8  22:42
 * GrowingUp  cn.servlet.post
 */
@WebServlet(name = "InsertPost", urlPatterns = "/insertPost")
public class InsertPost extends HttpServlet {
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
        String postContext = req.getParameter("postContext");
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        String email = (String) req.getSession().getAttribute("email");
        PrintWriter writer = resp.getWriter();
        if (email == null || email == "") {
            writer.println(false);
        } else {
            UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);
            String info = req.getParameter("info");
            String title = req.getParameter("title");
            Integer userId = mapper1.selectUserIdByEmail(email);
            Date format = new Date();
            Post post = new Post();
            post.setAuthor(userId);
            post.setTitle(title);
            post.setInfo(info);
            post.setContext(postContext);
            post.setTime(format);
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);
            try {
                Integer i = mapper.insertPost(post);
                System.out.println(i);
                if (i > 0) {
                    sqlSession.commit();
                    writer.println(true);
                }
            } finally {
                sqlSession.close();
            }
        }
    }
}
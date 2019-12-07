package cn.servlet.post;

import cn.mapper.PostMapper;
import cn.model.Post;
import cn.util.SqlSessionFactoryUtil;
import com.alibaba.fastjson.JSON;
import javafx.geometry.Pos;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 少杰
 * @create 2019/5/8  10:52
 * GrowingUp  cn.servlet.post
 */
@WebServlet(name = "SelectPostAllInfo", urlPatterns = "/selectPostAllInfo")
public class SelectPostAllInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            List<Post> posts = mapper.selectPostPagination();
            String postsJson = JSON.toJSONString(posts);
            writer.println(postsJson);
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}
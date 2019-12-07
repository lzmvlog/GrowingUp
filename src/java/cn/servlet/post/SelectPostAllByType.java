package cn.servlet.post;

import cn.mapper.PostMapper;
import cn.model.Post;
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
import java.util.List;

/**
 * @author 少杰
 * @create 2019/5/3  21:23
 * GrowingUp  cn.servlet.post
 */
@WebServlet(name = "SelectPostAllByType",urlPatterns = "/selectPostAllByType")
public class SelectPostAllByType extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取数据     对贴子的描述
        String postName = req.getParameter("info");
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            // 执行 SQL
            List<Post> posts = mapper.selectPostAllByType(postName);
            String postJson = JSON.toJSONString(posts);
            writer.println(postJson);
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

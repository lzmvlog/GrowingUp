package cn.servlet.tools;

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
 * @create 2019/5/9  22:42
 * GrowingUp  cn.servlet.tools
 */
@WebServlet(name = "JumpPage", urlPatterns = "/jumpPage")
public class JumpPage extends HttpServlet {
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
        Integer page = Integer.parseInt(req.getParameter("postCount")) ;
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            List<Post> posts = mapper.postPagination(page*5);
            String postsJson = JSON.toJSONString(posts);
            writer.println(postsJson);
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}
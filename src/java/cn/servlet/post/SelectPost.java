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

/**
 * @author 少杰
 * @create 2019/4/17  15:15
 * GrowingUp  cn.servlet.post
 */
@WebServlet(name = "SelectPost",urlPatterns = "/selectPost")
public class SelectPost extends HttpServlet {
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
        int postId = (int) req.getSession().getAttribute("postId");
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            Post post = mapper.selectPostFromId(postId);
            // 这里使用了 JSON 的 toJSONStringWithDateFormat() 方法 对日期时间类型做一个转换
            String postJson = JSON.toJSONStringWithDateFormat(post,"yyyy-MM-dd");
            writer.println(postJson);
        }finally{
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

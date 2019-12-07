package cn.servlet.comments;

import cn.mapper.CommentsMapper;
import cn.model.Comments;
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
 * @create 2019/4/29  15:17
 * GrowingUp  cn.servlet.comments
 */
@WebServlet(name = "SelectCommentsByOne",urlPatterns = "/selectCommentsByOne")
public class SelectCommentsByOne extends HttpServlet {
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
        Integer postId = Integer.parseInt(req.getParameter("postId"));
        // 读取数据
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        CommentsMapper mapper = sqlSession.getMapper(CommentsMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            List<Comments> comments = mapper.selectCommentsByOne(postId);
            // 这里使用了 JSON 的 toJSONStringWithDateFormat() 方法 对日期时间类型做一个转换
            String postJson = JSON.toJSONStringWithDateFormat(comments,"yyyy-MM-dd HH:mm:ss");
            writer.println(postJson);
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}

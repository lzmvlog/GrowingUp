package cn.servlet.post;

import cn.mapper.PostMapper;
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
 * @create 2019/4/27  16:27
 * GrowingUp  cn.servlet.post
 */
@WebServlet(name = "UpdatePostCount",urlPatterns = "/updatePostCount")
public class UpdatePostCount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text.html;charset=utf-8");
        // 获取数据 贴子的 id
        Integer postId = Integer.parseInt(req.getParameter("postId"));
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        try {
            Integer i = mapper.updatePostCount(postId);
            if (i > 0){
                sqlSession.commit();
            }
        }catch (Exception e){
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}

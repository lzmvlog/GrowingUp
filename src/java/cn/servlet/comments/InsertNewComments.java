package cn.servlet.comments;

import cn.mapper.CommentsMapper;
import cn.mapper.UserMapper;
import cn.model.Comments;
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
 * @create 2019/4/28  22:49
 * GrowingUp  cn.servlet.comments
 */
@WebServlet(name = "InsertNewComments",urlPatterns = "/insertNewComments")
public class InsertNewComments extends HttpServlet {
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
        String commentInfo = req.getParameter("comments");
        String email = (String) req.getSession().getAttribute("email");
        Date format = new Date();
        PrintWriter writer = resp.getWriter();
        if (email == null || email == ""){
            writer.println(false);
        }else{
            SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
            UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);
            Integer userId = mapper1.selectUserIdByEmail(email);
            Comments comments = new Comments();
            comments.setPostId(postId);
            comments.setUserId(userId);
            comments.setTime(format);
            comments.setCommentInfo(commentInfo);
            CommentsMapper mapper = sqlSession.getMapper(CommentsMapper.class);
            try {
                Integer i = mapper.insertNewComments(comments);
                if (i > 0){
                    sqlSession.commit();
                    writer.println(true);
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
}

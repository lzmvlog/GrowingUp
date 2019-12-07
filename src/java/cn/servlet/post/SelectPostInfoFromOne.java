package cn.servlet.post;

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
 * @create 2019/4/16  21:23
 * GrowingUp  cn.servlet.post
 */
@WebServlet(name = "SelectPostInfoFromOne",urlPatterns = "/selectPostInfoFromOne")
public class SelectPostInfoFromOne extends HttpServlet {
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
        int postId = Integer.parseInt(req.getParameter("postId"));
        PrintWriter writer = resp.getWriter();
        try {
            if (postId != -1){
                HttpSession session = req.getSession();
                session.setAttribute("postId",postId);
                writer.println("<script type='text/javascript'>location.href='postShow.html'</script>");
            }else{
                resp.sendRedirect("/GrowingUp/index.html");
            }
        }finally {
            writer.flush();
            writer.close();
        }
    }
}

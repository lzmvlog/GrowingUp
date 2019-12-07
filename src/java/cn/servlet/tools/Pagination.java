package cn.servlet.tools;

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
import java.util.ArrayList;
import java.util.List;

/**
 * @author 少杰
 * @create 2019/5/9  16:35
 * GrowingUp  cn.servlet.tools
 */
@WebServlet(name = "Pagination", urlPatterns = "/pagination")
public class Pagination extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 显示的分页
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            Integer i = mapper.selectPostCount();
            int count = (i%5) == 0 ? (i/5):((i/5)+1);
            List<Integer> pageSize = new ArrayList<Integer>();
            for (int j = 1; j <= count ; j++) {
                pageSize.add(j);
            }
            String pageSizeJson = JSON.toJSONString(pageSize);
            writer.println(pageSizeJson);
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}
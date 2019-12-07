package cn.servlet.advice;

import cn.mapper.AdviceMapper;
import cn.model.Advice;
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
 * @create 2019/5/7  22:33
 * GrowingUp  cn.servlet.advice
 */
@WebServlet(name = "SelectAdviceAllInfo", urlPatterns = "/admin/selectAdviceAllInfo")
public class SelectAdviceAllInfo extends HttpServlet {
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
        AdviceMapper mapper = sqlSession.getMapper(AdviceMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            List<Advice> advice = mapper.selectAdviceAllInfo();
            String adviceJson = JSON.toJSONString(advice);
            writer.println(adviceJson);
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}
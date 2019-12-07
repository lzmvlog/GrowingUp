package cn.servlet.advice;

import cn.mapper.AdviceMapper;
import cn.model.Advice;
import cn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

/**
 * @author 少杰
 * @create 2019/4/25  22:43
 * GrowingUp  cn.servlet.advice
 */
@WebServlet(name = "WriterAdvice",urlPatterns = "/writerAdvice")
public class WriterAdvice extends HttpServlet {
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
        // 获取建议内容
        String adviceInfo = req.getParameter("advice");
        String ip = InetAddress.getLocalHost().getHostAddress();
        Advice advice = new Advice();
        advice.setIp(ip);
        advice.setAdviceInfo(adviceInfo);
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        AdviceMapper mapper = sqlSession.getMapper(AdviceMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            Integer i = mapper.insertAdviceInfo(advice);
            if (i > 0){
                sqlSession.commit();
                writer.println(true);
            }else{
                writer.println(false);
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

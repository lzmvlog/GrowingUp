package cn.servlet.tools;

import cn.mapper.AdviceMapper;
import cn.mapper.UserMapper;
import cn.model.Advice;
import cn.model.User;
import cn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 少杰
 * @create 2019/5/7  20:42
 * GrowingUp  cn.servlet.tools
 */
@WebServlet(name = "ExportAdvice", urlPatterns = "/admin/exportAdvice")
public class ExportAdvice extends HttpServlet {
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
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        AdviceMapper mapper = sqlSession.getMapper(AdviceMapper.class);
        PrintWriter writer = resp.getWriter();
        try {
            List<Advice> advices = mapper.selectAdviceAllInfo();
            //创建一个Excel文档
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            //创建表头
            XSSFSheet xssfSheet = xssfWorkbook.createSheet("用户资料");
            XSSFRow header = xssfSheet.createRow(0);
            header.createCell(0).setCellValue("编号");
            header.createCell(1).setCellValue("电脑的ip地址");
            header.createCell(1).setCellValue("建议信息");

            //循环遍历用户数据
            for(int i = 0;i<advices.size();i++){
                Advice advice = advices.get(i);
                //创建一行
                XSSFRow row = xssfSheet.createRow(i+1);
                row.createCell(0).setCellValue(advice.getId());
                row.createCell(1).setCellValue(advice.getIp());
                row.createCell(1).setCellValue(advice.getAdviceInfo());
            }
            try {
                OutputStream outputStream = new FileOutputStream("C:\\Users\\少杰\\Desktop\\建议信息.xlsx");
                xssfWorkbook.write(outputStream);
                writer.println(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }
}
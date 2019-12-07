package cn.servlet.user;

import cn.mapper.UserMapper;
import cn.model.User;
import cn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

/**
 * @author 少杰
 * @create 2019/4/17  0:28
 * GrowingUp  cn.servlet.user
 */
@WebServlet(name = "UserForgetPassWord",urlPatterns = "/forget")
public class UserForgetPassWord extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    /**
     * 发件人邮箱地址
     */
    public static String myEmailAccount = "1591313226@qq.com";
    /**
     * 发件人邮箱密码     这里是生成的授权码
     */
    public static String myEmailPassword = "krnpvatdqtaghgfg";
    public static String myEmailSMTPHost = "smtp.qq.com";
    /**
     * 收件人的名字
      */
    public String personal = null;
    /**
     * 生成的 6 位验证码
     */
    public int number = -1;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取用户的注册邮箱  email 通过 email 来查询用的名称 以此来发送验证邮箱
        String email = req.getParameter("forgetEmail");
        number = (int) ((Math.random() * 9 + 1) * 100000);
        // 将产生的验证码放到 session 中
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("number",number);
        PrintWriter writer = resp.getWriter();
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectUserNameFromUserEmail(email);
            personal = user.getName();
            // 开始发送邮件
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            // 参数配置
            Properties props = new Properties();
            // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.transport.protocol", "smtp");
            // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.host", myEmailSMTPHost);
            props.setProperty("mail.smtp.auth", "true");
            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);
            // 设置为debug模式, 如果值为true则可以查看详细的发送 log
            session.setDebug(true);
            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, myEmailAccount, email);
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            // 5. 使用 邮箱账号 和 密码 连接邮件服务器--这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
            transport.connect(myEmailAccount, myEmailPassword);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            // 7. 关闭连接
            transport.close();
            if (number != -1){
                writer.println(true);
            }
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
            writer.flush();
            writer.close();
        }
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public  MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "少杰", "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "personal", "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject("重置密码", "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("<h1>重置密码：</h1><font color=\"red\"> 验证码：" + number + " <br/>请勿泄露你的验证码，该验证码在30分钟内有效！</font>", "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }
}

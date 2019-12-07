package cn.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 少杰
 * @create 2019/3/27  14:04
 * GrowingUp  cn.util
 */
public class SqlSessionFactoryUtil {
    private static final Class<SqlSessionFactoryUtil> LOCK = SqlSessionFactoryUtil.class;
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSessionFactory getSqlSessionFactory(){
        synchronized (LOCK){
            if (sqlSessionFactory != null) {
                return sqlSessionFactory;
            }
            InputStream inputStream = null;
            if (sqlSessionFactory == null) {
                try {
                    inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sqlSessionFactory;
    }
    public static SqlSession openSession(){
            return getSqlSessionFactory().openSession();
    }
}

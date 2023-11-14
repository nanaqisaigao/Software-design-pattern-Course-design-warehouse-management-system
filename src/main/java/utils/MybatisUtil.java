package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisUtil {

   private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            // 获取SqlMapConfig.xml文件作为Reader对象
            Reader reader = Resources.getResourceAsReader("config/SqlMapConfig.xml");
            // 使用SqlSessionFactoryBuilder构建SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession() {
        // 使用SqlSessionFactory的openSession()方法获取SqlSession对象
        return sqlSessionFactory.openSession();
    }
}

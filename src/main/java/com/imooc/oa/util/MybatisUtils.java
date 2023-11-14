package com.imooc.oa.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

public class MybatisUtils {
    //利用static(静态)属于类不属于对象,且全局唯一
    private static SqlSessionFactory sqlSessionFactory = null;
    //利用静态块在初始化类时实例化sqlSessionFactory
    static{
        Reader reader = null;
        try{
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(IOException e){
            //初始化错误时,通过抛出异常ExceptionInInitializerError通知调用者
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 执行SELECT查询SQL
     * @param func 要执行查询语句的代码块
     * @return 查询结果
     */
    //用于数据的查询[极大的简化查询] mybatis执行SQL时一定要有mapper的xml
    public static Object executeQuery(Function<SqlSession,Object> func){ //函数式接口
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{//具体查询交给Function实现 查询前完成连接的打开和关闭
            Object obj = func.apply(sqlSession);
            sqlSession.commit();
            return obj;
        }finally {
            sqlSession.close(); //最后一步释放连接资源
        }
    }

    /**
     * 执行INSERT/UPDATE/DELETE写操作SQL
     * @param func 要执行的写操作代码块
     * @return 写操作后返回的结果
     */
    public static Object executeUpdate(Function<SqlSession,Object> func){ //函数式接口
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try{//具体查询交给Function实现 查询前完成连接的打开和关闭
            Object obj = func.apply(sqlSession);
            sqlSession.commit();
            return obj;
        }catch (RuntimeException e){
            sqlSession.rollback();
            throw e;
        }finally {
            sqlSession.close(); //最后一步释放连接资源
        }
    }
}

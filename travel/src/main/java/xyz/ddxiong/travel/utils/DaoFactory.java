package xyz.ddxiong.travel.utils;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @author Lenovo
 */
public class DaoFactory {

    public static <T> T getBean(Class<T> daoInterface){
        return (T)Proxy.newProxyInstance(
                daoInterface.getClassLoader(),
                new Class[]{daoInterface},
                (proxy,method,args)->{

                    //得到会话
                    SqlSession sqlSession = MybatisUtils.getSqlSession();

                    //创建 DAO 代理类
                    T mapper = sqlSession.getMapper(daoInterface);

                    //调用 DAO 接口中的方法
                    Object obj = method.invoke(mapper, args);

                    //释放资源
                    MybatisUtils.closeSession(sqlSession);

                    //返回值
                    return obj;
                }
        );
    }
}

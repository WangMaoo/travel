package xyz.ddxiong.travel.service;

import org.apache.ibatis.session.SqlSession;
import xyz.ddxiong.travel.dao.UserMapper;
import xyz.ddxiong.travel.pojo.User;
import xyz.ddxiong.travel.utils.Md5Util;
import xyz.ddxiong.travel.utils.MybatisUtils;


/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月15日 20:05:00
 * @Description: TODO
 */
public class UserService {

    public boolean register(User user) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /**
         * 处理业务逻辑
         * 给用户的密码加密放入数据库中
         */
        String md5 = Md5Util.getMD5(user.getPassword());
        user.setPassword(md5);
        int register = mapper.register(user);
        sqlSession.close();
        return register > 0;
    }

    public User login(User user) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        user.setPassword(Md5Util.getMD5(user.getPassword()));
        User login = mapper.login(user);
        return login;
    }

    public boolean usernameStatus(String username) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.usernameStatus(username);
        return i > 0;
    }
}

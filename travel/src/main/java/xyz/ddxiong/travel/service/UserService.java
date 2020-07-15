package xyz.ddxiong.travel.service;

import org.apache.ibatis.session.SqlSession;
import xyz.ddxiong.travel.dao.UserMapper;
import xyz.ddxiong.travel.pojo.User;
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
        int register = mapper.register(user);
        sqlSession.close();
        return register > 0;
    }
}

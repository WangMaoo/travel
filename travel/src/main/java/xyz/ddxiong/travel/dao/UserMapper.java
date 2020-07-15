package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.pojo.User;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月15日 20:07:00
 * @Description: TODO
 */
public interface UserMapper {

    /**
     * 注册
     * @param user
     * @return
     */
    @Insert("insert into tab_user values(null,#{username},#{password},#{name},#{birthday},#{sex},#{telephone},#{email})")
    int register(User user);

    /**
     * 登陆
     * @param user
     * @return
     */
    @Select("select * from tab_user where username= #{username} and password=#{password}")
    User login(User user);

    /**
     * 检测是否已经注册
     * @param username
     * @return
     */
    @Select("select * from tab_user where username = #{username}")
    int usernameStatus(String username);
}

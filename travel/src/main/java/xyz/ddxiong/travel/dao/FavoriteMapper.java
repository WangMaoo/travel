package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.ddxiong.travel.pojo.Favorite;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月20日 18:12:00
 * @Description: TODO
 */
public interface FavoriteMapper {

    /**
     * 根据条件查询用户的收藏信息
     * @param uid
     * @param rid
     * @return
     */
    @Select("select count(*) from tab_favorite where uid=#{uid} and rid=#{rid}")
    int findFavoriteRoute(@Param("uid") int uid,@Param("rid") String rid);

    /**
     * 添加收藏
     * @param favorite
     * @return
     */
    @Insert("insert into tab_favorite value(#{rid},#{date},#{uid})")
    int addFavorite(Favorite favorite);

    /**
     * update次数
     * @param rid
     * @return
     */
    @Update("update tab_route set count=count+1 where rid=#{rid}")
    int updateCount(String rid);

    /**
     * 查询收藏次数
     * @param rid
     * @return
     */
    @Select("select count(*) from tab_route where rid=#{rid}")
    int findFavoriteCount(String rid);

    /**
     * 根据用户id查询出总条数
     * @param uid
     * @return
     */
    @Select("select count(*) from tab_favorite where uid=#{uid}")
    int getFavoriteCount(int uid);

    /**
     * 根据uid查询rid集合
     *
     * @param startIndex
     * @param pageSize
     * @param uid
     * @return
     */
    @Select("select rid from tab_favorite where uid=#{uid} limit #{startIndex},#{pageSize}")
    List<Integer> findFavoriteRid(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize,@Param("uid") int uid);
}

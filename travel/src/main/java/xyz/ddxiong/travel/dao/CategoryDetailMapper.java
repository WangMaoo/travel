package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.pojo.Category;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 19:49:00
 * @Description: TODO
 */
public interface CategoryDetailMapper {

    /**
     * 根据cid查询所有的信息
     * @param cid
     * @return
     */
    @Select("select * from tab_category where cid = #{cid}")
    Category findCategoryCid(int cid);
}

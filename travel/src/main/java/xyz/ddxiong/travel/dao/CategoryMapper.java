package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.pojo.Category;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月17日 18:14:00
 * @Description: TODO
 */
public interface CategoryMapper {
    /**
     * 查询所有分类
     * @return
     */
    @Select("select * from tab_category order by cid")
    List<Category> findAllCategory();
}

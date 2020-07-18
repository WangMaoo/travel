package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.pojo.RouteImg;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 19:58:00
 * @Description: TODO
 */
public interface RouteImageMapper {

    /**
     * 根据rid查询所有的线路图片
     * @param rid
     * @return
     */
    @Select("select * from tab_route_img where rid = #{rid}")
    List<RouteImg> findRouteImage(String rid);
}

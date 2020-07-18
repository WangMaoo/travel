package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.pojo.Route;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 19:45:00
 * @Description: TODO
 */
public interface RouteDetailMapper {

    /**
     * 根据rid查询线路详情
     * @param rid
     * @return
     */
    @Select("select * from tab_route where rid = #{rid}")
    Route findRouteDetailByRid(String rid);
}

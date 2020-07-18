package xyz.ddxiong.travel.service;

import xyz.ddxiong.travel.pojo.Route;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 19:42:00
 * @Description: TODO
 */
public interface RouteDetailService {

    /**
     * 根据rid查询信息
     * @param rid
     * @return
     */
    Route findRouteDetailByRid(String rid);
}

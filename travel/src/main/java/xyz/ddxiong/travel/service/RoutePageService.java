package xyz.ddxiong.travel.service;

import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月17日 20:36:00
 * @Description: TODO
 */
public interface RoutePageService {

    /**
     * 查询所有的信息
     * @param pageSize
     * @param pageNumber
     * @param cid
     * @return
     */
    PageBean<Route> findRoutePage(int pageSize, int pageNumber, String cid);

}

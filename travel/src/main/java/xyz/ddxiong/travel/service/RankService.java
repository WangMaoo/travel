package xyz.ddxiong.travel.service;

import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;


/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 21:44:00
 * @Description: TODO
 */
public interface RankService {
    /**
     * 根据条件查询信息
     * @param pageSize
     * @param pageNumber
     * @param rname
     * @param minPrice
     * @param maxPrice
     * @return
     */
    PageBean<Route> findRankRoute(int pageSize, int pageNumber, String rname, String minPrice, String maxPrice);

}

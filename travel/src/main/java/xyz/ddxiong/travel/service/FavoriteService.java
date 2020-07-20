package xyz.ddxiong.travel.service;

import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月20日 18:09:00
 * @Description: TODO
 */
public interface FavoriteService {

    /**
     * 根据条件查询用户的收藏信息
     * @param uid
     * @param rid
     * @return
     */
    Boolean findFavoriteRoute(int uid, String rid);


    /**
     * 添加收藏
     * @param uid
     * @param rid
     * @return
     */
    Boolean addFavoriteRoute(int uid, String rid);

    /**
     * 查看收藏次数
     * @param rid
     * @return
     */
    int findFavoriteCount(String rid);

    /**
     * 分页查看用户的收藏
     * @param pageSize
     * @param pageNumber
     * @param uid
     * @return
     */
    PageBean<Route> findFavoriteByPage(int pageSize, int pageNumber, int uid);
}

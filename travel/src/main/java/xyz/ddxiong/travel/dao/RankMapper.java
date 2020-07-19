package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Param;
import xyz.ddxiong.travel.pojo.Route;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 21:52:00
 * @Description: TODO
 */
public interface RankMapper {


    /**
     * 根据条件查询总条数
     * @param rname
     * @param minPrice
     * @param maxPrice
     * @return
     */
    int findTotalCount(@Param("rname") String rname, @Param("minPrice") String minPrice, @Param("maxPrice") String maxPrice);

    /**
     * 根据条件查询所有信息
     * @param startIndex
     * @param pageSize
     * @param rname
     * @param minPrice
     * @param maxPrice
     * @return
     */
    List<Route> findRouteTop(@Param("startIndex")int startIndex, @Param("pageSize")int pageSize, @Param("rname") String rname, @Param("minPrice") String minPrice, @Param("maxPrice") String maxPrice);
}

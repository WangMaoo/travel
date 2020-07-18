package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.pojo.Seller;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 19:53:00
 * @Description: TODO
 */
public interface SellerMapper {

    /**
     * 根据sid查询商家信息
     * @param sid
     * @return
     */
    @Select("select * from tab_seller where sid = #{sid}")
    Seller findSellerBySid(int sid);
}

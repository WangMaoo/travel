package xyz.ddxiong.travel.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.pojo.Route;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月17日 20:40:00
 * @Description: TODO
 */
public interface RouterPageMapper {

    /**
     * 根据cid查询总的条数
     *
     * @param cid
     * @param rname
     * @return
     */
   /* @Select("select count(*) from tab_route where cid = #{cid} and rflag=1 ")*/
    int findAllRoute(@Param("cid") String cid,@Param("rname") String rname);

    /**
     * 根据cid查询出线路数据并分页
     * @param startIndex
     * @param pageSize
     * @param cid
     * @param rname
     * @return
     */
   /* @Select("select * from tab_route where cid = #{cid} and rflag=1 limit #{startIndex},#{pageSize}")*/
    List<Route> findRouteByCid(@Param("startIndex") int startIndex,
                               @Param("pageSize") int pageSize,
                               @Param("cid") String cid,
                               @Param("rname") String rname);

}

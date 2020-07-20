package xyz.ddxiong.travel.service.impl;

import org.apache.ibatis.session.SqlSession;
import xyz.ddxiong.travel.dao.RankMapper;
import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.service.RankService;
import xyz.ddxiong.travel.utils.MybatisUtils;
import xyz.ddxiong.travel.utils.PageUtils;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 21:43:00
 * @Description: TODO
 */
public class RankServiceImpl implements RankService {

    @Override
    public PageBean<Route> findRankRoute(int pageSize, int pageNumber, String rname, String minPrice, String maxPrice) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RankMapper mapper = sqlSession.getMapper(RankMapper.class);
        int totalCount = mapper.findTotalCount(rname, minPrice, maxPrice);
        /**
         * 创建分页对象
         */
        PageBean<Route> pageBean = new PageBean<Route>(pageSize, totalCount);
        pageBean.setPageNumber(pageNumber);
        /**
         * 调用方法查询所有符合的信息
         */
        int startIndex = (pageNumber - 1) * pageSize;
        List<Route> routeTop = mapper.findRouteTop(startIndex, pageSize, rname, minPrice, maxPrice);
        pageBean.setData(routeTop);

        /**
         * 设置前五后四
         */
        int[] pagination = PageUtils.pagination(pageNumber, totalCount);
        pageBean.setStart(pagination[0]);
        pageBean.setEnd(pagination[1]);
        MybatisUtils.closeSession(sqlSession);
        return pageBean;
    }
}

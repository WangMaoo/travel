package xyz.ddxiong.travel.service.impl;

import org.apache.ibatis.session.SqlSession;
import xyz.ddxiong.travel.dao.CategoryDetailMapper;
import xyz.ddxiong.travel.dao.RouteDetailMapper;
import xyz.ddxiong.travel.dao.RouteImageMapper;
import xyz.ddxiong.travel.dao.SellerMapper;
import xyz.ddxiong.travel.pojo.Category;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.pojo.RouteImg;
import xyz.ddxiong.travel.pojo.Seller;
import xyz.ddxiong.travel.service.RouteDetailService;
import xyz.ddxiong.travel.utils.MybatisUtils;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 19:44:00
 * @Description: TODO
 */
public class RouteDetailServiceImpl implements RouteDetailService {

    @Override
    public Route findRouteDetailByRid(String rid) {
        /**
         * 查询路线
         */
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RouteDetailMapper mapper = sqlSession.getMapper(RouteDetailMapper.class);
        Route routeDetailByRid = mapper.findRouteDetailByRid(rid);
        /**
         * 查询线路分类信息
         */
        CategoryDetailMapper categoryMapper = sqlSession.getMapper(CategoryDetailMapper.class);
        Category categoryDetail = categoryMapper.findCategoryCid(routeDetailByRid.getCid());
        /**
         * 查询商家信息
         */
        SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
        Seller sellerBySid = sellerMapper.findSellerBySid(routeDetailByRid.getSid());
        /**
         * 查询图片信息
         */
        RouteImageMapper routeImageMapper = sqlSession.getMapper(RouteImageMapper.class);
        List<RouteImg> routeImageList = routeImageMapper.findRouteImage(rid);
        /**
         * 封装信息到Route类中并返回
         */
        routeDetailByRid.setCategory(categoryDetail);
        routeDetailByRid.setSeller(sellerBySid);
        routeDetailByRid.setRouteImgList(routeImageList);

        MybatisUtils.closeSession(sqlSession);
        return routeDetailByRid;
    }
}
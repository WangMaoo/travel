package xyz.ddxiong.travel.service.impl;

import org.apache.ibatis.session.SqlSession;
import xyz.ddxiong.travel.dao.FavoriteMapper;
import xyz.ddxiong.travel.dao.RouteDetailMapper;
import xyz.ddxiong.travel.pojo.Favorite;
import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.service.FavoriteService;
import xyz.ddxiong.travel.service.RouteDetailService;
import xyz.ddxiong.travel.utils.MybatisUtils;
import xyz.ddxiong.travel.utils.PageUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月20日 18:11:00
 * @Description: TODO
 */
public class FindFavoriteRouteImpl implements FavoriteService {

    @Override
    public Boolean findFavoriteRoute(int uid, String rid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavoriteMapper mapper = sqlSession.getMapper(FavoriteMapper.class);
        int favoriteRoute = mapper.findFavoriteRoute(uid, rid);
        MybatisUtils.closeSession(sqlSession);
        return favoriteRoute > 0;
    }

    @Override
    public Boolean addFavoriteRoute(int uid, String rid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavoriteMapper mapper = sqlSession.getMapper(FavoriteMapper.class);
        Favorite favorite = new Favorite();
        favorite.setRid(Integer.parseInt(rid));
        favorite.setUid(uid);
        /**
         * 创建一个日期
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        favorite.setDate(format);
        int i = mapper.addFavorite(favorite);
        /**
         * 将tab_route中的count加一
         */
        int j = mapper.updateCount(rid);
        MybatisUtils.closeSession(sqlSession);
        return i > 0 && j > 0;
    }

    @Override
    public int findFavoriteCount(String rid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavoriteMapper mapper = sqlSession.getMapper(FavoriteMapper.class);
        int favoriteCount = mapper.findFavoriteCount(rid);
        MybatisUtils.closeSession(sqlSession);
        return favoriteCount;
    }

    @Override
    public PageBean<Route> findFavoriteByPage(int pageSize, int pageNumber, int uid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavoriteMapper mapper = sqlSession.getMapper(FavoriteMapper.class);
        int favoriteCount = mapper.getFavoriteCount(uid);
        RouteDetailMapper routeMapper = sqlSession.getMapper(RouteDetailMapper.class);
        PageBean<Route> pageBean = new PageBean<>(pageSize, favoriteCount);
        pageBean.setPageNumber(pageNumber);
        int startIndex = (pageNumber - 1) * pageSize;
        /**
         * 根据uid分页查询出rid
         */
        List<Integer> favoriteRid = mapper.findFavoriteRid(startIndex,pageSize,uid);
        /**
         * 遍历rid集合求出所有的线路详情
         */
        List<Route> routeList = new ArrayList<>();
        for (int routeRid: favoriteRid) {
            Route route = routeMapper.findRouteDetailByRid(routeRid + "");
            routeList.add(route);
        }
        /**
         * 封装数据
         */
        pageBean.setData(routeList);
        int[] pagination = PageUtils.pagination(pageNumber, pageBean.getTotalPage());
        pageBean.setStart(pagination[0]);
        pageBean.setEnd(pagination[1]);
        MybatisUtils.closeSession(sqlSession);
        return pageBean;
    }
}

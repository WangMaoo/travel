package xyz.ddxiong.travel.service.impl;

import org.apache.ibatis.session.SqlSession;
import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.service.RoutePageService;
import xyz.ddxiong.travel.dao.RouterPageMapper;
import xyz.ddxiong.travel.utils.MybatisUtils;
import xyz.ddxiong.travel.utils.PageUtils;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月17日 20:39:00
 * @Description: TODO
 */
public class RoutePageServiceImpl implements RoutePageService {

    @Override
    public PageBean<Route> findRoutePage(int pageSize, int pageNumber, String cid) {
        /**
         * 获取总的条数
         */
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RouterPageMapper mapper = sqlSession.getMapper(RouterPageMapper.class);
        int totalCount = mapper.findAllRoute(cid);
        /**
         * 封装page的信息
         */
        PageBean<Route> pageBean = new PageBean<Route>(pageSize, totalCount);
        pageBean.setPageNumber(pageNumber);
        int startIndex = (pageNumber -1)*pageSize;
        /**
         * 根据起始索引和每页显示的条数和条件查询线路
         */
        List<Route> routeByCid = mapper.findRouteByCid(startIndex, pageSize, cid);
        pageBean.setData(routeByCid);

        /**
         * 计算开始页和结束页
         */
        int[] pagination = PageUtils.pagination(pageNumber, pageBean.getTotalPage());
        pageBean.setStart(pagination[0]);
        pageBean.setEnd(pagination[1]);
        MybatisUtils.closeSession(sqlSession);
        return pageBean;
    }
}

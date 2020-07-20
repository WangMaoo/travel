package xyz.ddxiong.travel.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.ResultInfo;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.pojo.User;
import xyz.ddxiong.travel.service.FavoriteService;
import xyz.ddxiong.travel.service.RouteDetailService;
import xyz.ddxiong.travel.utils.BeanFactory;
import xyz.ddxiong.travel.web.bean.BeanServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月20日 15:26:00
 * @Description: TODO
 */
@WebServlet("/favoriteServlet")
public class FavoriteServlet extends BeanServlet {

    public void getFavoriteStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /**
         * 判断是否登陆
         */
        ResultInfo resultInfo = new ResultInfo();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.getWriter().print(0);
            return;
        }
        /**
         * 根据uid和rid查询用户是否收藏了此线路
         */
        String rid = request.getParameter("rid");
        int uid = user.getUid();
        FavoriteService favoriteService = (FavoriteService) BeanFactory.getBean("FavoriteService");
        Boolean flag = favoriteService.findFavoriteRoute(uid, rid);
        if (flag) {
            response.getWriter().print(1);
            return;
        }
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();
        if (user == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户没有登陆");
            String s = resultInfo.toJson(resultInfo);
            response.getWriter().print(s);
            return;
        }
        /**
         * 根据uid和rid向表中插入数据
         */
        String rid = request.getParameter("rid");
        int uid = user.getUid();
        FavoriteService favoriteService = (FavoriteService) BeanFactory.getBean("FavoriteService");
        Boolean flag = favoriteService.addFavoriteRoute(uid, rid);
        /**
         * 查询收藏的次数
         */
        if (flag) {
            RouteDetailService routeDetailService = (RouteDetailService) BeanFactory.getBean("RouteDetailService");
            Route route = routeDetailService.findRouteDetailByRid(rid);
            resultInfo.setFlag(true);
            resultInfo.setSuccessData(route);
            String s = resultInfo.toJson(resultInfo);
            response.getWriter().print(s);
            return;
        }
    }

    public void findFavoriteByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        int pageSize = 10;
        int pageNumber= 1;
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        /**
         * 根据uid查询出rid的集合
         */
        FavoriteService favoriteService = (FavoriteService) BeanFactory.getBean("FavoriteService");
        PageBean<Route> favoriteByPage = favoriteService.findFavoriteByPage(pageSize, pageNumber, uid);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(favoriteByPage);
        response.getWriter().print(s);

    }

}
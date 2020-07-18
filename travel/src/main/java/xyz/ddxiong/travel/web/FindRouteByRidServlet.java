package xyz.ddxiong.travel.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.service.RouteDetailService;
import xyz.ddxiong.travel.utils.BeanFactory;
import xyz.ddxiong.travel.web.bean.BeanServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月18日 19:28:00
 * @Description: TODO
 */
@WebServlet("/findRouteByRidServlet")
public class FindRouteByRidServlet extends BeanServlet {

    public void findRouteRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String rid = request.getParameter("rid");
        System.out.println("rid = " + rid);
        RouteDetailService routeDetailService = (RouteDetailService) BeanFactory.getBean("RouteDetailService");
        /**
         * 调用方法,转换json,返回数据
         */
        Route routeDetail = routeDetailService.findRouteDetailByRid(rid);
        String s = new ObjectMapper().writeValueAsString(routeDetail);
        System.out.println("s = " + s);
        response.getWriter().print(s);
    }

}
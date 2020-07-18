package xyz.ddxiong.travel.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.service.RoutePageService;
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
 * @createTime 2020年07月17日 20:32:00
 * @Description: TODO
 */
@WebServlet("/findRoutePageServlet")
public class FindRoutePageServlet extends BeanServlet {

    public void findRoutePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        int pageSize = 10;
        int pageNumber = 1;
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (Exception e) {
            pageSize = 10;
        }
        try {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        } catch (Exception e) {
            pageNumber = 1;
        }
        String cid = request.getParameter("cid");
        String rname = request.getParameter("rname");
        RoutePageService routePageService = (RoutePageService) BeanFactory.getBean("RoutePageService");
        PageBean<Route> routePage = routePageService.findRoutePage(pageSize, pageNumber, cid,rname);
        /**
         * 将routePage对象转换为json数据返回
         */
        ObjectMapper objectMapper = new ObjectMapper();
        String RoutePageJson = objectMapper.writeValueAsString(routePage);
        response.getWriter().print(RoutePageJson);
    }
}
package xyz.ddxiong.travel.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.ddxiong.travel.pojo.PageBean;
import xyz.ddxiong.travel.pojo.Route;
import xyz.ddxiong.travel.service.RankService;
import xyz.ddxiong.travel.service.impl.RankServiceImpl;
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
 * @createTime 2020年07月18日 21:20:00
 * @Description: TODO
 */
@WebServlet("/rankTopServlet")
public class RankTopServlet extends BeanServlet {

    public void findRankTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        /**
         * 获取前台的数据
         */
        int pageSize = 6;
        int pageNumber = 1;
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (Exception e) {
            pageSize = 6;
        }
        try {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        } catch (Exception e) {
            pageNumber = 1;
        }
        String rname = request.getParameter("rname");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        /**
         * 调用方法
         */
        RankService rankService = (RankService) BeanFactory.getBean("RankService");
        PageBean<Route> rankRoute = rankService.findRankRoute(pageSize, pageNumber, rname, minPrice, maxPrice);

        /**
         * 将得到的数据转换为json
         */
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(rankRoute);
        response.getWriter().print(s);
    }

}
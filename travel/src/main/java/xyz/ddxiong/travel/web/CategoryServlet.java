package xyz.ddxiong.travel.web;

import xyz.ddxiong.travel.service.CategoryService;
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
 * @createTime 2020年07月17日 18:07:00
 * @Description: TODO
 */
@WebServlet("/categoryServlet")
public class CategoryServlet extends BeanServlet {

    public void findCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 将获取到的信息转换为json返回到前端
         * 这里为了解耦,利用工厂+反射+配置文件
         */
        response.setContentType("application/json;charset=utf-8");
        CategoryService categoryService = (CategoryService)BeanFactory.getBean("CategoryService");
        String category = categoryService.findCategory();
        response.getWriter().print(category);
    }

}
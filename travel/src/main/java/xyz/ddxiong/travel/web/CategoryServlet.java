package xyz.ddxiong.travel.web;

import xyz.ddxiong.travel.service.CategoryService;
import xyz.ddxiong.travel.utils.BeanFactory;

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
public class CategoryServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        CategoryService categoryService = (CategoryService)BeanFactory.getBean("CategoryService");
        String category = categoryService.findCategory();
        System.out.println(category);
        response.getWriter().print(category);
    }

}
package xyz.ddxiong.travel.web;

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
@WebServlet("/RankTopServlet")
public class RankTopServlet extends BeanServlet {

    public void findRankTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
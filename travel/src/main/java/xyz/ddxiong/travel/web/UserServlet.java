package xyz.ddxiong.travel.web;

import org.apache.commons.beanutils.BeanUtils;
import xyz.ddxiong.travel.pojo.User;
import xyz.ddxiong.travel.service.UserService;
import xyz.ddxiong.travel.web.bean.BeanServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月15日 20:00:00
 * @Description: TODO
 */
@WebServlet("/userServlet")
public class UserServlet extends BeanServlet {
    public void register(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,parameterMap);
            UserService userService = new UserService();
            boolean flag = userService.register(user);
            if (flag) {
                response.getWriter().print(1);
            } else {
                response.getWriter().print(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
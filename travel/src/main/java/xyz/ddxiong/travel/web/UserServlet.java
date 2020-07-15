package xyz.ddxiong.travel.web;

import org.apache.commons.beanutils.BeanUtils;
import xyz.ddxiong.travel.pojo.ResultInfo;
import xyz.ddxiong.travel.pojo.User;
import xyz.ddxiong.travel.service.UserService;
import xyz.ddxiong.travel.web.bean.BeanServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
            /**
             * 获取表单信息封装到实体类中
             * 调用service处理业务逻辑
             * 响应处理得到的结果
             */
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

    public void login(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 获取页面中的验证码和session中的验证码比较
         */
        response.setContentType("application/json;charset=utf-8");
        try {
            String check = request.getParameter("check");
            HttpSession session = request.getSession();
            String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");
            session.removeAttribute("CHECKCODE_SERVER");
            ResultInfo resultInfo = new ResultInfo();
            if (check == null || "".equals(check)) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("验证码为空");
                /**
                 * 将resultInfo对象转化为json返回
                 */
                String s = resultInfo.toJson(resultInfo);
                response.getWriter().print(s);
                return;
            }
            if (!check.equalsIgnoreCase(checkcode_server)) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("验证码不正确");
                String s = resultInfo.toJson(resultInfo);
                response.getWriter().print(s);
                return;
            }

            /**
             * 获取页面的数据封装到user中,处理业务逻辑
             */
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, parameterMap);
            UserService userService = new UserService();
            User loginUser = userService.login(user);
            /**
             * 对返回的用户进行处理
             */
            if (loginUser == null) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户名或密码不正确");
            } else {
                resultInfo.setFlag(true);
                session.setAttribute("user",loginUser);
                /**
                 * session时间设置30分钟
                 */
                Cookie cookie = new Cookie("JSESSIONID",session.getId());
                cookie.setMaxAge(30 * 60);
                cookie.setPath(request.getContextPath()+"/");
                response.addCookie(cookie);
            }
            String s = resultInfo.toJson(resultInfo);
            response.getWriter().print(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginStatus(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 获取session中的用户
         * 逻辑判断,将得到数据返回给前端页面
         */
        response.setContentType("application/json;charset=utf-8");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            ResultInfo resultInfo = new ResultInfo();
            if (user == null) {
                resultInfo.setFlag(false);
            } else {
                resultInfo.setFlag(true);
                resultInfo.setSuccessData(user);
            }
            String s = resultInfo.toJson(resultInfo);
            response.getWriter().print(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath()+"/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void usernameStatus(HttpServletRequest request,HttpServletResponse response) {
        try {
            String username = request.getParameter("usernameStatus");
            System.out.println(username);
            UserService userService = new UserService();
            boolean flag = userService.usernameStatus(username);
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
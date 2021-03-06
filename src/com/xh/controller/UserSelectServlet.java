package com.xh.controller;

import com.alibaba.fastjson.JSONObject;
import com.xh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserSelectServlet", urlPatterns = "/UserSelectServlet")
public class UserSelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.修正编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        //2.接收两个参数  page limit
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        String real_name = req.getParameter("real_name");
        System.out.println("real_name = " + real_name);
        String type = req.getParameter("type");
        System.out.println("type = " + type);
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        //把收到的参数  封装到map中
        Map map1 = new HashMap();
        map1.put("page", page);
        map1.put("limit", limit);
        map1.put("real_name", real_name);
        map1.put("type", type);
        map1.put("username", username);


        //3.调用service
        UserService userService = new UserService();
        Map map = userService.selectAllByParam(map1);

        //4.把map json
        String s = JSONObject.toJSONString(map);
        //5.流输出
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(s);
        printWriter.close();
    }
}

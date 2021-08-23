package com.xh.controller;

import com.alibaba.fastjson.JSON;
import com.xh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "UserUpdateForDelServlet", urlPatterns = "/UserUpdateForDelServlet")
public class UserUpdateForDelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正 编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错

        //2.接收参数
        String sfDel = req.getParameter("sfDel");
        String userId = req.getParameter("userId");

        //找bug 的  基础
        System.out.println("sfDel = " + sfDel);
        System.out.println("userId = " + userId);

        //调用  service 层
        UserService userService = new UserService();
        Map map = userService.updateUserById(Integer.parseInt(sfDel), Integer.parseInt(userId));
        String s = JSON.toJSONString(map);

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(s);
        printWriter.close();
    }
}

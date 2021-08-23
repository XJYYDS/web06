package com.xh.service;

import com.xh.bean.User;
import com.xh.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    // 登陆
    public Map login(String username, String password, HttpServletRequest request) {
        Map map = new HashMap();
        // service 层要调用dao层
        UserDAO dao = new UserDAO();
        User userFromDB = dao.login(username, password);
        if (null == userFromDB) {
            // 没查不出, 即:账户名或者密码不正确
            map.put("code", 4001);
            map.put("msg", "账户名或者密码不正确");
            return map;
        } else {
            // 当登陆成功后,需要把 登陆的用户的信息放入到  session中去
            HttpSession session = request.getSession();
            session.setAttribute("user", userFromDB);
            map.put("code", 0);
            map.put("msg", "登陆成功");
            return map;
        }

    }

    //带参数的分页查询
    public Map selectAllByParam(Map map1) {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.selectAllByParam(map1);
        int i = userDAO.selectCount(map1);
        Map map = new HashMap();
        // map.put("code",0);
        map.put("code111", 200);  //返回的数据不符合规范，正确的状态码是  code,0
        map.put("msg111", "查询成功");
        map.put("count111", i);  //把死的写活了
        map.put("data111", users);

        //根据layui的返回的 json格式 去返回给你的数据，如果不一样，需要layui解析
        // {
        //  code:0,
        //  msg:" "
        // count:1000
        //data:[每条数据]
        // }
        Map map2 = new HashMap();
        map2.put("number", 20001);
        map2.put("message", "数据查询成功");
        map2.put("object", map);


        return map2;

    }

    //修改  是否可用
    public Map updateUserById(Integer sfDel, Integer userId) {
        UserDAO dao = new UserDAO();
        int i = dao.updateUserById(sfDel, userId);
        Map map = new HashMap();

        if (i == 1) {
            map.put("code", 0);
            map.put("msg", "修改成功");
        } else {
            map.put("code", 400);
            map.put("msg", "修改不成功");
        }
        return map;
    }

    //新增
    public Map insertByUser(User user) {
        Map map = new HashMap();
        // service 层要调用dao层
        UserDAO dao = new UserDAO();
        System.out.println("dao = " + dao);
        System.out.println("user = " + user);

        int a = dao.addUser(user);
        System.out.println("a = " + a);
        if (a > 0) {
            System.out.println(" 提交成功");
            map.put("code", 0);
            map.put("msg", "添加成功");
        } else {
            System.out.println(" 提交失败");
            map.put("code", 4002);
            map.put("msg", "添加失败");
        }
        return map;
    }

    //修改
    public Map updateUser(User user1) {
        System.out.println("进入  service");
        System.out.println("user1 = " + user1);

        Map map1 = new HashMap();
        // service 层要调用dao层
        UserDAO dao = new UserDAO();
        System.out.println("dao = " + dao);

        int a = dao.update(user1);
        System.out.println("a = " + a);
        if (a > 0) {
            System.out.println(" 提交成功");
            map1.put("code", 0);
            map1.put("msg", "修改成功");
        } else {
            System.out.println(" 提交失败");
            map1.put("code", 4003);
            map1.put("msg", "修改失败");
        }
        return map1;

    }

    //删除
    public Map delUser(User user2) {
        System.out.println("进入 删除的 service");
        System.out.println("user2 = " + user2);

        Map map1 = new HashMap();
        // service 层要调用dao层
        UserDAO dao = new UserDAO();
        System.out.println("dao = " + dao);

        int a = dao.del(user2);
        System.out.println("a = " + a);
        if (a > 0) {
            System.out.println(" 提交成功");
            map1.put("code", 0);
            map1.put("msg", "删除成功");
        } else {
            System.out.println(" 提交失败");
            map1.put("code", 4004);
            map1.put("msg", "删除失败");
        }
        return map1;

    }


}



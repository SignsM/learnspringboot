package com.signs123.baiyunbbs.controller;

import com.alibaba.fastjson.JSON;
import com.signs123.baiyunbbs.bean.User;
import com.signs123.baiyunbbs.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/checkUserByAjax/{uName}")
    public String CheckUserByAjax(@PathVariable String uName) {

        User user = new User();
        user.setuName(uName);
        try {
            User user1 = userService.FindUserByName(user);
            if (user1 == null) {
                return JSON.toJSONString("yes");
            } else {
                return JSON.toJSONString("exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString("exists");
        }


    }
}

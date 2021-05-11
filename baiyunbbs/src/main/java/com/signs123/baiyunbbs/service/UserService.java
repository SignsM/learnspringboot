package com.signs123.baiyunbbs.service;

import com.signs123.baiyunbbs.bean.User;

public interface UserService {

    User selectUserByName(User user);


    void addUser(User user);

    User FindUserByName(User user);

    void updateUser(User user);

    User selectUserById(int uId);
}

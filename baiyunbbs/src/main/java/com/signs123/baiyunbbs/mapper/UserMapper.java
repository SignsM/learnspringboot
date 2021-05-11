package com.signs123.baiyunbbs.mapper;

import com.signs123.baiyunbbs.bean.User;




public interface UserMapper {
    User selectUserByName(User user);


    void addUser(User user);


    User FindUserByName(User user);

    void updateUser(User user);


    User selectUserById(int uId);

}

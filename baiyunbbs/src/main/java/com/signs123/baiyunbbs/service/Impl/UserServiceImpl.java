package com.signs123.baiyunbbs.service.Impl;

import com.signs123.baiyunbbs.bean.User;
import com.signs123.baiyunbbs.mapper.UserMapper;
import com.signs123.baiyunbbs.service.UserService;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component(value = "userService")
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User selectUserByName(User user) {

        return userMapper.selectUserByName(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User FindUserByName(User user) {
        return userMapper.FindUserByName(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User selectUserById(int uId) {
        return userMapper.selectUserById(uId);
    }
}

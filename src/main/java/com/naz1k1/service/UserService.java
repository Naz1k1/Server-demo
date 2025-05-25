package com.naz1k1.service;


import com.mybatisflex.core.query.QueryWrapper;
import com.naz1k1.entity.User;
import com.naz1k1.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User test() {



        QueryWrapper queryWrapper =QueryWrapper.create()
                .select()
                .from(User.class)
                .where(User::getUsername).ge("root");
        return userMapper.selectOneByQuery(queryWrapper);
    }
}

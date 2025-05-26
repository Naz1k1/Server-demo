package com.naz1k1.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.naz1k1.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naz1k1.mapper.UserMapper;

@Service
public class AuthService {

    private static final String USER_SESSION_PREFIX = "session:user:";

    private final UserMapper userMapper;
    private RedisTemplate<String, Object> redisTemplate;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserMapper userMapper, RedisTemplate<String, Object> redisTemplate, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String username, String password) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .from(User.class)
                .where(User::getUsername).eq(username)
                .and(User::getPassword).eq(passwordEncoder.encode(password))
                .and(User::getDeleted).eq(0);
        return userMapper.selectOneByQuery(queryWrapper);
    }

}

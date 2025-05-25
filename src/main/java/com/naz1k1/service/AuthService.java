package com.naz1k1.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.naz1k1.mapper.UserMapper;

@Service
public class AuthService {

    private static final String USER_SESSION_PREFIX = "session:user:";

    private final UserMapper userMapper;
    private RedisTemplate<String, Object> redisTemplate;

    public AuthService(UserMapper userMapper, RedisTemplate<String, Object> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    
    


}

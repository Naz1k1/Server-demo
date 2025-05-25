package com.naz1k1.service;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.StringUtil;
import com.naz1k1.entity.User;
import com.naz1k1.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名查询用户(非逻辑删除用户)
     * @author Naz1k1
     * @version 1.0.0
     * @param username
     * @return User
     */
    public User getUserByUsername(String username) {
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(User.class)
            .where(User::getUsername).eq(username)
            .and(User::getDeleted).eq(0);
        return userMapper.selectOneByQuery(query);
    }

    /**
     * 根据用户ID查询用户
     * @author Naz1k1
     * @version 1.0.0
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        return userMapper.selectOneById(id);
    }

    /**
     * 分页查询用户
     * @author Naz1k1
     * @version 1.0.0
     * @param page
     * @param username
     * @param phone
     * @param email
     * @return
     */
    public Page<User> getUserPage(Page<User> page, String username, String phone, String email) {
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(User.class)
            .where(User::getDeleted).eq(0);

        return userMapper.paginate(page, query);
    }
}

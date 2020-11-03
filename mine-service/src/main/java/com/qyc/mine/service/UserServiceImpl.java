package com.qyc.mine.service;

import com.qyc.mine.api.UserService;
import com.qyc.mine.api.model.UserModel;
import com.qyc.mine.dao.dataobject.UserDO;
import com.qyc.mine.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
public class UserServiceImpl implements UserService {

    private static final BeanCopier copier = BeanCopier.create(UserModel.class, UserDO.class, false);
    @Autowired
    private UserMapper userMapper;

    public String getUserName(Long id) {
        UserDO userDO = userMapper.getById(id);
        return userDO != null ? userDO.getName() : null;
    }

    public UserModel addUser(UserModel user) {
        UserDO userDO = new UserDO();
        copier.copy(user, userDO, null);

        Long id = userMapper.insert(userDO);
        user.setId(id);
        return user;
    }
}

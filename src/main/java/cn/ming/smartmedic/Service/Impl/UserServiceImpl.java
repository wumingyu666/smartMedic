package cn.ming.smartmedic.Service.Impl;

import cn.ming.smartmedic.Service.UserService;
import cn.ming.smartmedic.dao.UserDao;
import cn.ming.smartmedic.domain.User;
import cn.ming.smartmedic.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;

    @Override
    public User checkUser(String username, String password) {
        return userdao.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}

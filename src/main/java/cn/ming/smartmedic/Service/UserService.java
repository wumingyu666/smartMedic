package cn.ming.smartmedic.Service;

import cn.ming.smartmedic.domain.User;

public interface UserService {
    public User checkUser(String username, String password);
}

package cn.ming.smartmedic.dao;

import cn.ming.smartmedic.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findByUsernameAndPassword(String name, String password);
}

package com.peanutmain.curry.dao;

import com.peanutmain.curry.model.User;
import org.apache.ibatis.annotations.Param;

public interface IUserDao {

    int insert(User user);

    int update(User user);

    int delete(@Param("id") Long id);

    User findByUsername(@Param("username") String username);
}

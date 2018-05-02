package com.peanutmain.curry.service.impl;

import com.peanutmain.curry.dao.IUserDao;
import com.peanutmain.curry.model.User;
import com.peanutmain.curry.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}

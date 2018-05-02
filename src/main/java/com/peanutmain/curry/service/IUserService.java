package com.peanutmain.curry.service;

import com.peanutmain.curry.model.User;

public interface IUserService {

    User add(User user);

    void update(User user);

    void delete(Long id);

    User findByUsername(String username);
}

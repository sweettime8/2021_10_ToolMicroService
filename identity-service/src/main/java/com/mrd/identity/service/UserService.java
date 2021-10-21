package com.mrd.identity.service;

import com.mrd.identity.entity.User;

/**
 * @author ducnh
 */
public interface UserService {
    User findByUserName(String userName);

    User findByEmail(String email);

    User findByMobile(String mobile);

    void save(User user);
}

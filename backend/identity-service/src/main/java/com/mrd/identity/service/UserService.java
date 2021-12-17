package com.mrd.identity.service;

import com.mrd.identity.entity.User;
import com.mrd.identity.entity.dto.UserSearchForm;

import java.util.List;

/**
 * @author ducnh
 */
public interface UserService {
    User findByUserName(String userName);

    List<User> search(UserSearchForm searchForm, int currentPage, int rowPerPage);

    User findByUuid(String uuid);

    User findByEmail(String email);

    User findByMobile(String mobile);

    long count(UserSearchForm searchForm);

    void save(User user);

}

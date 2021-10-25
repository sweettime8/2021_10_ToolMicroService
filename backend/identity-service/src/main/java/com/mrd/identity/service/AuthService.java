package com.mrd.identity.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ducnh
 */
public interface AuthService {
    UserDetails loadUserByUuid(String uuid);
}

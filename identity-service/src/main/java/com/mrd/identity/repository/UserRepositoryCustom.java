package com.mrd.identity.repository;

import com.mrd.identity.entity.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 * @author ducnh
 */
@Repository
public class UserRepositoryCustom extends BaseRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryCustom.class);

    @Autowired
    public UserRepositoryCustom(EntityManagerFactory factory) {
        super(factory);
    }

    public User findByUuid(String uuid) {
        Session session = openSession();
        try {
            User user = session.load(User.class, uuid);
            return user;
        } catch (EntityNotFoundException ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return null;
    }


    public User findByUserName(String username) {
        Session session = openSession();
        Object result = null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM user WHERE username = ?", User.class);
            query.setParameter(1, username);
            result = query.getSingleResult();
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return result != null ? (User) result : null;
    }

    public User findByEmail(String email) {
        Session session = openSession();
        Object result = null;
        try {
            Query query = session.createNativeQuery(" SELECT * FROM user WHERE email = ? ", User.class);
            query.setParameter(1, email.trim());
            result = query.getSingleResult();
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return result != null ? (User) result : null;
    }

    public User findByMobile(String mobile) {
        Session session = openSession();
        Object result = null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM user WHERE mobile = ?", User.class);
            query.setParameter(1, mobile);
            result = query.getSingleResult();
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return result != null ? (User) result : null;
    }


    public User findByEmailOrMobile(String userInfo) {
        Session session = openSession();
        Object result = null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM user WHERE "
                    + " (email = ? AND is_delete = 0) OR (mobile = ? AND is_delete = 0) ", User.class);
            query.setParameter(1, userInfo);
            query.setParameter(2, userInfo);
            result = query.getSingleResult();
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return result != null ? (User) result : null;
    }
}

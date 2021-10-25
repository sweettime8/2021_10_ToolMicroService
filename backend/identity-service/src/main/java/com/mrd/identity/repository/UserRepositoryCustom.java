package com.mrd.identity.repository;

import com.mrd.identity.entity.User;
import com.mrd.identity.entity.dto.UserSearchForm;
import com.mrd.identity.utils.StringUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Object result = null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM user WHERE uuid = ?", User.class);
            query.setParameter(1, uuid);
            result = query.getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return result != null ? (User) result : null;
    }

    public List<User> search(UserSearchForm data) {
        List<User> userList = null;
        TypedQuery<User> query = this.buildSearchQuery(data, User.class, false);
        super.initPaging(query, data.getCurrentPage(), data.getRowsPerPage());
        return query.getResultList();
    }

    private <T> TypedQuery<T> buildSearchQuery(final UserSearchForm data,
                                               final Class<T> clazz, final boolean count) {
        String baseQuery = "Select u from User u where u.isDelete = 0";
        if (count) {
            baseQuery = "Select count(u) from User u where u.isDelete = 0";
        }
        StringBuilder sql = new StringBuilder(baseQuery);
        Map<String, Object> params = new HashMap<>();

        if (!StringUtil.isNullOrEmpty(data.getKeyword())) {
            sql.append("and o.username like(:keyword");
            params.put("keyword", "%" + data.getKeyword() + "%");
        }
        return super.createQuery(sql.toString(), params, clazz);

    }

    public long count(UserSearchForm searchForm){
        TypedQuery<Long> query = this.buildSearchQuery(searchForm,Long.class,true);
        return query.getSingleResult();
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

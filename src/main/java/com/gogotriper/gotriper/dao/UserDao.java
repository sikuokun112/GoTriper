package com.gogotriper.gotriper.dao;


import com.gogotriper.gotriper.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDao {
    @Autowired
    private EntityManager entityManager;

    public Account findUserAccount(String userName) {
        try {
            String sql = "Select e from " + Account.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, Account.class);
            query.setParameter("userName", userName);

            return (Account) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

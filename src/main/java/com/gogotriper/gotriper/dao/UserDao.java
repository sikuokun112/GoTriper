package com.gogotriper.gotriper.dao;


import com.gogotriper.gotriper.entity.TaiKhoan;
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

    public TaiKhoan findUserAccount(String userName) {
        try {
            String sql = "Select e from " + TaiKhoan.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, TaiKhoan.class);
            query.setParameter("userName", userName);

            return (TaiKhoan) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

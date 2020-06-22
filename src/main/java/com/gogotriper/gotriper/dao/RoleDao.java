package com.gogotriper.gotriper.dao;


import com.gogotriper.gotriper.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDao {
    @Autowired
    private EntityManager entityManager;

    public List<String> getRoleNames(Long userId) {
        String sql = "Select r.role_name from role r, user_role ur where ur.role_id = r.role_id AND ur.user_id = ";
        sql+= userId+"";
        Query query = entityManager.createNativeQuery(sql);
        //query.setParameter("name", u.getUserName())
        return query.getResultList();
    }

    public Role findRoleById(Long roleId){
        String sql = "Select * from " + Role.class.getName() + " r " //
                + " where r.roleId= :roleId ";

        Query query = this.entityManager.createQuery(sql, Role.class);
        query.setParameter("roleId", roleId);
        return (Role) query.getResultList().get(0);

    }
}

package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,String> {
    public Role findAllByRoleId(Long roleId);
}

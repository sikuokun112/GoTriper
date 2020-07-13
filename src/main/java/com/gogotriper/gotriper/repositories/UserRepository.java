package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Account,String> {

    List<Account> findByUserNameContaining(String userName);

    Account findByUserName(String userName);
    Account findByUserId(Long id);
}

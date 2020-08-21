package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Account,String> {

    List<Account> findByUserNameContaining(String userName);

    @Query(value = "SELECT  * FROM account" , nativeQuery = true)
    List<Account> getAllAccount();

    Account findByUserName(String userName);
    Account findByUserId(Long id);
}

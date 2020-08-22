package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.TaiKhoan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<TaiKhoan,String> {

    List<TaiKhoan> findByUserNameContaining(String userName);

    @Query(value = "SELECT  * FROM account" , nativeQuery = true)
    List<TaiKhoan> getAllAccount();

    TaiKhoan findByUserName(String userName);
    TaiKhoan findByUserId(Long id);
}

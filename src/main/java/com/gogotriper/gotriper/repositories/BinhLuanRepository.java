package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.BinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BinhLuanRepository extends JpaRepository<BinhLuan,String> {
    @Query(value = "SELECT  * FROM binhluan b WHERE b.baidang_id = ?1 ORDER BY b.thoigian DESC" , nativeQuery = true)
    List<BinhLuan> getAllBinhLuanOfBaiDang(int id);
}

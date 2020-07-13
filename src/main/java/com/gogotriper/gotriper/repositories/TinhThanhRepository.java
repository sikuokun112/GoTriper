package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.TinhThanh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TinhThanhRepository extends JpaRepository<TinhThanh,String> {
    TinhThanh findById(int id);
    TinhThanh findByTen(String ten);
}

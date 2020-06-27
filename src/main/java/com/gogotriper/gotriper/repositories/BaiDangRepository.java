package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.BaiDang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaiDangRepository extends JpaRepository<BaiDang,String> {
    public BaiDang findBaiDangById(int id);
}

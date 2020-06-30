package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanhMucRepository extends JpaRepository<DanhMuc, String>  {
    DanhMuc findDanhMucByTenDanhMuc(String tenDanhMuc);
    DanhMuc findDanhMucById(int id);
}

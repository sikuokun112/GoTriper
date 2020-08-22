package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.DiaDiem;
import com.gogotriper.gotriper.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<HinhAnh, String> {
    public List<HinhAnh> findAllByBaiDangImage(BaiDang baiDangImage);
    public List<HinhAnh> findAllByDiaDiemImage(DiaDiem diaDiemImage);
}

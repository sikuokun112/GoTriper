package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaiDangRepository extends JpaRepository<BaiDang,String> {
    public BaiDang findBaiDangById(int id);
    public List<BaiDang> findAllByDanhMuc(DanhMuc danhMuc);
    @Query("select b from BaiDang b inner join b.diaDiem d  inner join b.danhMuc dm where  b.tieuDe LIKE %?1% OR d.tenDiaDiem LIKE %?1% OR dm.tenDanhMuc LIKE %?2%")
    public List<BaiDang> findAllBaiDangSearchByTen(String keyword ,String keyword2);
}

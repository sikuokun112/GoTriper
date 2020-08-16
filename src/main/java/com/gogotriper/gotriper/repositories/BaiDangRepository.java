package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.DanhMuc;
import com.gogotriper.gotriper.entity.DiaDiem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaiDangRepository extends JpaRepository<BaiDang,String> {
    public BaiDang findBaiDangById(int id);
    public List<BaiDang> findAllByDanhMuc(DanhMuc danhMuc);
    @Query("select b from BaiDang b inner join b.diaDiem d  inner join b.danhMuc dm inner join d.tinhThanh t where  b.tieuDe LIKE %?1% OR t.ten LIKE %?1% OR d.tenDiaDiem LIKE %?1% OR dm.tenDanhMuc LIKE %?2%")
    public List<BaiDang> findAllBaiDangSearchByTen(String keyword ,String keyword2);

    @Query(value = "select * from baidang b ORDER BY  b.soluotxem desc LIMIT 3",nativeQuery = true)
    public List<BaiDang> findTop3BaiDangByViews();

    @Query(value = "select * from baidang b,(SELECT bl.baidang_id as blbdid, sum(bl.binhluan_id) as tongbl from binhluan bl GROUP BY bl.baidang_id) AS totals WHERE  totals.blbdid = b.baidang_id  order by totals.tongbl desc Limit 3",nativeQuery = true)
    public List<BaiDang> findTop3BaiDangByBinhLuan();

    public List<BaiDang> findAllByDiaDiem(DiaDiem diaDiem);


}

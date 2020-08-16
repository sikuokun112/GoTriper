package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.DiaDiem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaDiemRepository extends JpaRepository<DiaDiem, String> {
    public DiaDiem findDiaDiemById(int id);
    @Query("select d from DiaDiem d inner join d.tinhThanh t  where  d.tenDiaDiem LIKE %?1% OR t.ten LIKE %?1%")
    public List<DiaDiem> findAllListByName(String tendiadiem);
}

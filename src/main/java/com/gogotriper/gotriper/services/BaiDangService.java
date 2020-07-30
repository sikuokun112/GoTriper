package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.DanhMuc;
import com.gogotriper.gotriper.repositories.BaiDangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaiDangService {

    @Autowired
    private BaiDangRepository baiDangRepository;

    public void saveBaiDang(BaiDang baiDang){
        baiDangRepository.save(baiDang);
    }
    public BaiDang findBaiDangById(int id){
        return  baiDangRepository.findBaiDangById(id);
    }
    public List<BaiDang>  findListBaiDangByDanhMuc(DanhMuc danhMuc){
        return baiDangRepository.findAllByDanhMuc(danhMuc);
    }

    public void deleteBaiDang(BaiDang baiDang){
        baiDangRepository.delete(baiDang);
    }

    public List<BaiDang> findAllBaiDangSearchByName(String name1, String name2){
        return baiDangRepository.findAllBaiDangSearchByTen(name1,name2);
    }
}

package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.repositories.BaiDangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

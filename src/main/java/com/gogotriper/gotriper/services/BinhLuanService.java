package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.BinhLuan;
import com.gogotriper.gotriper.repositories.BinhLuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinhLuanService {
    @Autowired
    private BinhLuanRepository binhLuanRepository;
    public void saveBinhLuan(BinhLuan binhLuan){
        binhLuanRepository.save(binhLuan);
    }

    public List<BinhLuan> getAllBinhLuanOfBaiDang(int id){
       return binhLuanRepository.getAllBinhLuanOfBaiDang(id);
    }
}

package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.TinhThanh;
import com.gogotriper.gotriper.repositories.TinhThanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TinhThanhService {
    @Autowired
    TinhThanhRepository tinhThanhRepository;
    public List<TinhThanh> getAllTinhThanh(){
        return tinhThanhRepository.findAll();
    }
    public TinhThanh getTinhThanhById(int id){

        return tinhThanhRepository.findById(id);
    }
}

package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.DanhMuc;
import com.gogotriper.gotriper.repositories.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;

    public List<DanhMuc> getAllDanhMuc(){
        return  danhMucRepository.findAll();
    }
    public DanhMuc findDanhMucByName(String name){
        return danhMucRepository.findDanhMucByTenDanhMuc(name);
    }
    public DanhMuc findDanhMucById(int id){
        return danhMucRepository.findDanhMucById(id);
    }
    public void saveDanhMuc(DanhMuc danhMuc){
        danhMucRepository.save(danhMuc);
    }
    public void removeDanhMuc(DanhMuc danhMuc){
        danhMucRepository.delete(danhMuc);
    }
}

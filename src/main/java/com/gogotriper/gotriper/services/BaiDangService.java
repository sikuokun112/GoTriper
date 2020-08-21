package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.DanhMuc;
import com.gogotriper.gotriper.entity.DiaDiem;
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

    public List<BaiDang> findAllBaiDangByDiaDiem(DiaDiem diaDiem){
        return baiDangRepository.findAllByDiaDiem(diaDiem);

    }
    public void deleteBaiDang(BaiDang baiDang){
        baiDangRepository.delete(baiDang);
    }

    public List<BaiDang> findAllBaiDangSearchByName(String name1, String name2){
        return baiDangRepository.findAllBaiDangSearchByTen(name1,name2);
    }

    public  List<BaiDang> findTop3BaiDangByViews(){
        return baiDangRepository.findTop3BaiDangByViews();
    }
    public  List<BaiDang> findTop3BaiDangByBinhLuan(){
        return baiDangRepository.findTop3BaiDangByBinhLuan();
    }

    public List<BaiDang> getAll(){
        return baiDangRepository.findAll();
    }
    public void removeBaiDang(BaiDang baiDang){
        baiDangRepository.delete(baiDang);
    }

}

package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.DiaDiem;
import com.gogotriper.gotriper.repositories.DiaDiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaDiemService  {
    @Autowired
    private DiaDiemRepository diaDiemRepository;
    public void saveDiaDiem(DiaDiem diaDiem){
        diaDiemRepository.save(diaDiem);
    }

    public DiaDiem findDiaDiemById(int id){
        return diaDiemRepository.findDiaDiemById(id);
    }

    public List<DiaDiem> findAllListByName(String tendiadiem){
        return diaDiemRepository.findAllListByName(tendiadiem);
    }

    public List<DiaDiem> findAll(){
        return diaDiemRepository.findAll();
    }

    public void removeDiaDiem(DiaDiem diaDiem){
        diaDiemRepository.delete(diaDiem);
    }
}

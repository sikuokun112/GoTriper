package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.DiaDiem;
import com.gogotriper.gotriper.repositories.DiaDiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

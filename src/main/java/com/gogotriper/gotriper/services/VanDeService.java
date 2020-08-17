package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.VanDe;
import com.gogotriper.gotriper.repositories.VanDeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VanDeService {
    @Autowired
    private VanDeRepository vanDeRepository;

    public void saveVanDe(VanDe vanDe){
        vanDeRepository.save(vanDe);
    }
}

package com.gogotriper.gotriper.repositories;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {
    public List<Image> findAllByBaiDangImage(BaiDang baiDangImage);
}

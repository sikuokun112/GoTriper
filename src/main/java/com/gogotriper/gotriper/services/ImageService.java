package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.DiaDiem;
import com.gogotriper.gotriper.entity.HinhAnh;
import com.gogotriper.gotriper.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(HinhAnh hinhAnh){
        imageRepository.save(hinhAnh);
    }
    public void saveListImage(List<HinhAnh> listHinhAnhs){
        for (HinhAnh hinhAnh : listHinhAnhs){
            imageRepository.save(hinhAnh);
        }
    }
    public int getLatestIdImage(){
        List<HinhAnh> hinhAnhs = imageRepository.findAll();
        if(hinhAnhs.size()!=0){
        return hinhAnhs.get(hinhAnhs.size()-1).getId();
        }
        else{
            return 0;
        }

    }
    public void saveImageToUploads(MultipartFile file,int index){
        Path path = Paths.get("uploads/");
        try{
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream,path.resolve(index+"_"+file.getOriginalFilename()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<HinhAnh> findAllImageByBaiDang(BaiDang baiDang){
        return imageRepository.findAllByBaiDangImage(baiDang);
    }
    public List<HinhAnh> findAllImageByDiaDiem(DiaDiem diaDiem){
        return imageRepository.findAllByDiaDiemImage(diaDiem);
    }

}

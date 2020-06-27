package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.Image;
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

    public void saveImage(Image image){
        imageRepository.save(image);
    }
    public void saveListImage(List<Image> listImages){
        for (Image image: listImages){
            imageRepository.save(image);
        }
    }
    public int getLatestIdImage(){
        List<Image> images = imageRepository.findAll();
        if(images.size()!=0){
        return images.get(images.size()-1).getId();
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
    public List<Image> findAllImageByBaiDang(BaiDang baiDang){
        return imageRepository.findAllByBaiDangImage(baiDang);
    }

}

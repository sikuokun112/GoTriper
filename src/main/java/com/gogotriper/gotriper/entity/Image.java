package com.gogotriper.gotriper.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    @Column(name = "imageurl")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "diadiem_id")
    private DiaDiem diaDiem_image;

    @ManyToOne
    @JoinColumn(name = "baidang_id")
    private BaiDang baiDangImage;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DiaDiem getDiaDiem_image() {
        return diaDiem_image;
    }

    public void setDiaDiem_image(DiaDiem diaDiem_image) {
        this.diaDiem_image = diaDiem_image;
    }


    public BaiDang getBaiDangImage() {
        return baiDangImage;
    }

    public void setBaiDangImage(BaiDang baiDangImage) {
        this.baiDangImage = baiDangImage;
    }
}

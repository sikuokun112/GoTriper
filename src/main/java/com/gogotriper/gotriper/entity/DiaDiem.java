package com.gogotriper.gotriper.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diadiem")
public class DiaDiem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diadiem_id")
    private int id;
    @Column(name = "tendiadiem")
    private String tenDiaDiem;
    @Column(name = "gia")
    private int gia;

    @OneToMany(mappedBy = "diaDiem")
    private List<BaiDang> listBaiDang_DiaDiem;

    @OneToMany(mappedBy = "diaDiem_image")
    private List<Image> listImage_DiaDiem;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }


    public List<BaiDang> getListBaiDang_DiaDiem() {
        return listBaiDang_DiaDiem;
    }

    public void setListBaiDang_DiaDiem(List<BaiDang> listBaiDang_DiaDiem) {
        this.listBaiDang_DiaDiem = listBaiDang_DiaDiem;
    }

    public List<Image> getListImage_DiaDiem() {
        return listImage_DiaDiem;
    }

    public void setListImage_DiaDiem(List<Image> listImage_DiaDiem) {
        this.listImage_DiaDiem = listImage_DiaDiem;
    }
}

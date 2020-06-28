package com.gogotriper.gotriper.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "danhmuc")
public class DanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "danhmuc_id")
    private int id;

    @Column(name = "tendanhmuc")
    private String tenDanhMuc;

    @OneToMany(mappedBy = "danhMuc")
    private List<BaiDang> listBaiDang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }


    public List<BaiDang> getListBaiDang() {
        return listBaiDang;
    }

    public void setListBaiDang(List<BaiDang> listBaiDang) {
        this.listBaiDang = listBaiDang;
    }
}

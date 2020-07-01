package com.gogotriper.gotriper.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "baidang")
public class BaiDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baidang_id")
    private int id;
    @Column(name = "tieude")
    private String tieuDe;

    @Column(name = "noidung")
    private String noiDung;
    @ManyToOne
    @JoinColumn(name = "danhmuc_id")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "diadiem_id")
    private DiaDiem diaDiem;

    @OneToMany(mappedBy = "baiDangImage",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Image> listImage_BaiDang;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account userId;

    @OneToMany(mappedBy = "baiDang")
    private List<BinhLuan> listBinhLuans;



    public Account getUserId() {
        return userId;
    }

    public void setUserId(Account userId) {
        this.userId = userId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }



    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }



    public DiaDiem getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }

    public List<Image> getListImage_BaiDang() {
        return listImage_BaiDang;
    }

    public void setListImage_BaiDang(List<Image> listImage_BaiDang) {
        this.listImage_BaiDang = listImage_BaiDang;
    }

    public List<BinhLuan> getListBinhLuans() {
        return listBinhLuans;
    }

    public void setListBinhLuans(List<BinhLuan> listBinhLuans) {
        this.listBinhLuans = listBinhLuans;
    }
}

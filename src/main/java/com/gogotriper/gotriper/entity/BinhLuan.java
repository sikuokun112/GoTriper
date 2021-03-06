package com.gogotriper.gotriper.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "binhluan")
public class BinhLuan {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "binhluan_id")
    private int id;
    @Column(name = "tieude")
    private String tieude;
    @Column(name = "noidung")
    private String noidung;
    @Column(name = "diemdanhgia")
    private Float diemdanhgia;

    @Column(name = "thoigian")
    private Timestamp thoigian;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TaiKhoan userId;

    @ManyToOne
    @JoinColumn(name = "baidang_id")
    private BaiDang baiDang;
    public BaiDang getBaiDang() {
        return baiDang;
    }
    public void setBaiDang(BaiDang baiDang) {
        this.baiDang = baiDang;
    }

    public TaiKhoan getUserId() {
        return userId;
    }

    public void setUserId(TaiKhoan userId) {
        this.userId = userId;
    }

    public Float getDiemdanhgia() {
        return diemdanhgia;
    }

    public void setDiemdanhgia(Float diemdanhgia) {
        this.diemdanhgia = diemdanhgia;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getThoigian() {
        return thoigian;
    }

    public void setThoigian(Timestamp thoigian) {
        this.thoigian = thoigian;
    }
}

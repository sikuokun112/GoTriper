package com.gogotriper.gotriper.entity;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account userId;

    @ManyToOne
    @JoinColumn(name = "baidang_id")
    private BaiDang baiDang;
    public BaiDang getBaiDang() {
        return baiDang;
    }
    public void setBaiDang(BaiDang baiDang) {
        this.baiDang = baiDang;
    }

    public Account getUserId() {
        return userId;
    }

    public void setUserId(Account userId) {
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
}

package com.gogotriper.gotriper.entity;


import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "giamin")
    private int giamin;
    @Column(name = "giamax")
    private int giamax;
    @Column(name = "giohoatdong")
    private Date gioHoatDong;
    @Column(name = "giodongcua")
    private Date gioDongCua;
    @Column(name = "kinhdo")
    private String kinhDo;
    @Column(name = "vido")
    private String viDo;
    @Column(name = "diachi")
    private String diaChi;
    @Column(name = "sdt")
    private int sdt;

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Column(name = "noidung")
    private String noiDung;


    @OneToMany(mappedBy = "diaDiem")
    private List<BaiDang> listBaiDang_DiaDiem;

    @OneToMany(mappedBy = "diaDiemImage",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Image> listImage_DiaDiem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account userId;

    public Account getUserId() {
        return userId;
    }

    public void setUserId(Account userId) {
        this.userId = userId;
    }




    public int getGiamin() {
        return giamin;
    }

    public void setGiamin(int giamin) {
        this.giamin = giamin;
    }

    public int getGiamax() {
        return giamax;
    }

    public void setGiamax(int giamax) {
        this.giamax = giamax;
    }

    public Date getGioHoatDong() {
        return gioHoatDong;
    }

    public void setGioHoatDong(Date gioHoatDong) {
        this.gioHoatDong = gioHoatDong;
    }

    public Date getGioDongCua() {
        return gioDongCua;
    }

    public void setGioDongCua(Date gioDongCua) {
        this.gioDongCua = gioDongCua;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

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

    public String getViDo() {
        return viDo;
    }

    public void setViDo(String viDo) {
        this.viDo = viDo;
    }
}

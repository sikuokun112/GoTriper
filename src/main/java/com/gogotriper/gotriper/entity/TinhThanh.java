package com.gogotriper.gotriper.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tinhthanh")
public class TinhThanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tinhthanh_id")
    private int id;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "tinhThanh",cascade = CascadeType.ALL)
    private List<DiaDiem> listDiaDiems;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DiaDiem> getListDiaDiems() {
        return listDiaDiems;
    }

    public void setListDiaDiems(List<DiaDiem> listDiaDiems) {
        this.listDiaDiems = listDiaDiems;
    }
}

package com.gogotriper.gotriper.entity;

import javax.persistence.*;

@Entity
@Table(name = "vande")
public class VanDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vande_id")
    private int id;

    @Column(name = "tieude")
    private String tieude;

    @Column(name = "noidung")
    private String noidung;

    @Column(name = "flag")
    private int flag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account userIdVanDe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Account getUserIdVanDe() {
        return userIdVanDe;
    }

    public void setUserIdVanDe(Account userIdVanDe) {
        this.userIdVanDe = userIdVanDe;
    }
}

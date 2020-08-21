package com.gogotriper.gotriper.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "account",uniqueConstraints = {@UniqueConstraint(name = "account_user_UK",columnNames = "username")})
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username",length = 45)
    private String userName;

    @Column(name = "password",length = 128)
    private String passWord;

    @Column(name = "fullname",length = 128)
    private String fullName;

    @Column(name = "email",length = 128)
    private String email;

    @Column(name = "enable",length = 1)
    private boolean enable;
    public Long getUserId() {
        return userId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns={
            @JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private List<Role> roles;

    @OneToMany(mappedBy = "userId")
    private List<BaiDang> listBaiDang;

    @OneToMany(mappedBy = "userId")
    private List<DiaDiem> listDiaDiem;

    @OneToMany(mappedBy = "userId")
    private List<BinhLuan> listBinhLuans;

    @OneToMany(mappedBy = "userIdVanDe")
    private List<VanDe> listVanDes;

    @OneToMany(mappedBy = "userImage",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Image> listUserImages;
    public List<DiaDiem> getListDiaDiem() {
        return listDiaDiem;
    }
    public void setListDiaDiem(List<DiaDiem> listDiaDiem) {
        this.listDiaDiem = listDiaDiem;
    }



    public List<BaiDang> getListBaiDang() {
        return listBaiDang;
    }

    public void setListBaiDang(List<BaiDang> listBaiDang) {
        this.listBaiDang = listBaiDang;
    }



    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    public List<BinhLuan> getListBinhLuans() {
        return listBinhLuans;
    }

    public void setListBinhLuans(List<BinhLuan> listBinhLuans) {
        this.listBinhLuans = listBinhLuans;
    }

    public List<Image> getListUserImages() {
        return listUserImages;
    }

    public void setListUserImages(List<Image> listUserImages) {
        this.listUserImages = listUserImages;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<VanDe> getListVanDes() {
        return listVanDes;
    }

    public void setListVanDes(List<VanDe> listVanDes) {
        this.listVanDes = listVanDes;
    }


}

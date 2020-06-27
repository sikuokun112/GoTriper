package com.gogotriper.gotriper.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account",uniqueConstraints = {@UniqueConstraint(name = "account_user_UK",columnNames = "username")})
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username",length = 45)
    private String userName;

    @Column(name = "password",length = 128)
    private String passWord;

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



}

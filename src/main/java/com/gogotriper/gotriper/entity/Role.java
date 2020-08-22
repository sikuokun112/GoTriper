package com.gogotriper.gotriper.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role",uniqueConstraints = {@UniqueConstraint(name = "role_user_UK",columnNames = "role_name")})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name",length = 30)
    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private List<TaiKhoan> taiKhoans;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public List<TaiKhoan> getAccounts() {
        return taiKhoans;
    }

    public void setAccounts(List<TaiKhoan> taiKhoans) {
        this.taiKhoans = taiKhoans;
    }
}

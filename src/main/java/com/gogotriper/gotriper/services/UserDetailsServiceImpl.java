package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.dao.RoleDao;
import com.gogotriper.gotriper.dao.UserDao;
import com.gogotriper.gotriper.entity.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = this.userDao.findUserAccount(userName);
        if(taiKhoan == null){
            System.out.println("User not found! "+userName);
            throw new UsernameNotFoundException("User "+userName+" was not found in data");
        }
        System.out.println("Found User: "+ taiKhoan);
        List<String> roleNames = this.roleDao.getRoleNames(taiKhoan.getUserId());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(roleNames != null){
            for(String role: roleNames){
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(taiKhoan.getUserName(), taiKhoan.getPassWord(),grantList);
        return userDetails;
    }
}

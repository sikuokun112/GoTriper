package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.entity.TaiKhoan;
import com.gogotriper.gotriper.entity.Role;
import com.gogotriper.gotriper.repositories.RoleRepository;
import com.gogotriper.gotriper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
   private RoleRepository roleRepository;



    public void createUser(TaiKhoan taiKhoan) {
        BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
        taiKhoan.setPassWord(encoder.encode(taiKhoan.getPassWord()));
        taiKhoan.setEnable(true);
        Role role = roleRepository.findAllByRoleId(Integer.toUnsignedLong(2));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        taiKhoan.setRoles(roles);
        userRepository.save(taiKhoan);
    }

    public void saveUser(TaiKhoan taiKhoan){
        userRepository.save(taiKhoan);
    }
//    public void createAdmin(Account account) {
//        BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
//        account.setPassWord(encoder.encode(account.getPassWord()));
//        Role userRole = new Role();
//        userRole.setRoleName("ROLE_ADMIN1");
//        List<Role> roles = new ArrayList<>();
//        roles.add(userRole);
//        //account.setRoles(roles);
//        userRepository.save(account);
//    }

    public TaiKhoan findOne(String userName) {

       // return userRepository.getOne(userName);
        return null;
    }

    public boolean isUserPresent(String userName) {
        // TODO Auto-generated method stub
        TaiKhoan u= userRepository.findByUserName(userName);
        if(u!=null) {
            return true;
        }
        return false;
    }

    public TaiKhoan findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public TaiKhoan findByUserId(Long id ){
        return  userRepository.findByUserId(id);
    }

    public List<TaiKhoan> getAllAccount(){
        return userRepository.getAllAccount();
    }
    public void removeAccount(TaiKhoan taiKhoan){
        userRepository.delete(taiKhoan);
    }

}

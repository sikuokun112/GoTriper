package com.gogotriper.gotriper.services;

import com.gogotriper.gotriper.dao.RoleDao;
import com.gogotriper.gotriper.entity.Account;
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



    public void createUser(Account account) {
        BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
        account.setPassWord(encoder.encode(account.getPassWord()));
        account.setEnable(true);
        Role role = roleRepository.findAllByRoleId(Integer.toUnsignedLong(2));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        account.setRoles(roles);
        userRepository.save(account);
    }

    public void saveUser(Account account){
        userRepository.save(account);
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

    public Account findOne(String userName) {

       // return userRepository.getOne(userName);
        return null;
    }

    public boolean isUserPresent(String userName) {
        // TODO Auto-generated method stub
        Account u= userRepository.findByUserName(userName);
        if(u!=null) {
            return true;
        }
        return false;
    }

    public Account findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public Account findByUserId(Long id ){
        return  userRepository.findByUserId(id);
    }

}

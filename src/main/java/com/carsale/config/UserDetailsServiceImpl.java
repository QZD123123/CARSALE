package com.carsale.config;

import com.carsale.mapper.UserMapper;
import com.carsale.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userMapper.findByPhone(phoneNumber);
        System.out.println("user = " + user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        return new org.springframework.security.core.userdetails.User(
//            user.getPhone(),
//            user.getPassword(),
//            getAuthorities(user.getRole())
//        );
        return user;
    }
    
//    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
//        System.out.println("role = " + role);
//        return Collections.singleton(new SimpleGrantedAuthority(role));
//    }
}
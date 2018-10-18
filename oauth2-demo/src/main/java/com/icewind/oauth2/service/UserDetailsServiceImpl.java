package com.icewind.oauth2.service;

import com.icewind.oauth2.entity.SecurityUserDetails;
import com.icewind.oauth2.entity.User;
import com.icewind.oauth2.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icewind
 * @date 2018/1/15
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        authorities.add(new SimpleGrantedAuthority("manage"));
        SecurityUserDetails userDetails = new SecurityUserDetails(user.getUsername(), user.getPassword(), authorities);
        userDetails.setSex(String.valueOf(user.getSex()));
        return userDetails;
    }
}

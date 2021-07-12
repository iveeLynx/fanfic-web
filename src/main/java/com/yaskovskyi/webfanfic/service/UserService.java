package com.yaskovskyi.webfanfic.service;

import com.yaskovskyi.webfanfic.model.User;
import com.yaskovskyi.webfanfic.model.UserData;
import com.yaskovskyi.webfanfic.util.HttpService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = HttpService.getInstance().getUser(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserData(user);
    }
}

package com.brahalla.Cerberus.service.impl;

import com.brahalla.Cerberus.model.factory.CerberusUserFactory;
import com.brahalla.Cerberus.model.security.CerberusUser;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CerberusUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOne(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return CerberusUserFactory.create(user);
        }
    }

}

package cgg.blogapp.blogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cgg.blogapp.blogapp.entities.CustomUserDetails;
import cgg.blogapp.blogapp.entities.User;
import cgg.blogapp.blogapp.repos.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User u1 = repo.findByName(username);

        if (u1 == null) {

            throw new UsernameNotFoundException("no user found");

        }

        return new CustomUserDetails(u1);

    }

}

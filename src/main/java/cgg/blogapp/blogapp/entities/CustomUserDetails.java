package cgg.blogapp.blogapp.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    public User u2;

    public CustomUserDetails(User u1) {
        // TODO Auto-generated constructor stub
        u2 = u1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> auths = new HashSet<SimpleGrantedAuthority>();

        auths.add(new SimpleGrantedAuthority(u2.getRole()));

        return auths;
    }

    @Override
    public String getPassword() {
        return u2.getPassword();
    }

    @Override
    public String getUsername() {
        return u2.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
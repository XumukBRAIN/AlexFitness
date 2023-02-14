package com.example.crossFit.security;

import com.example.crossFit.model.entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CoachDetails implements UserDetails {

    private final Coach coach;

    @Autowired
    public CoachDetails(Coach coach) {
        this.coach = coach;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(coach.getRole()));
    }

    @Override
    public String getPassword() {
        return coach.getPassword();
    }

    @Override
    public String getUsername() {
        return coach.getPhoneNumber();
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

    public Coach getCoach() {
        return this.coach;
    }
}

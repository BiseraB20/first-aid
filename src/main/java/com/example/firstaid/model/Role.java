package com.example.firstaid.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_KID, ROLE_ADMIN, ROLE_ADULT;
    //bez ova ne raboteshe kaj User klasata metodot  getAuthorities()
    @Override
    public String getAuthority() {
        return name();//vrakja edno od gore navedenite
    }
}
package priv.eternasparkle.service.impl;

import priv.eternasparkle.DTO.UserDetailDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private UserDetailDTO detailsDTO;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(){}
    public UserDetailsImpl(UserDetailDTO dto){
        System.out.println(dto.getPermission());
        this.detailsDTO = dto;
        List<String> perms = this.detailsDTO.getPermission();
        setPermissions(perms);
    }
    private void setPermissions(List<String> perms) {
        List<GrantedAuthority> list = new ArrayList<>();
        for (String p : perms) {
            SimpleGrantedAuthority auth = new SimpleGrantedAuthority(p);
            list.add(auth);
        }
        this.authorities = list;
    }

    public UserDetailDTO getUserDetailsDTO(){
        return detailsDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return detailsDTO.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return detailsDTO.getUser().getUsername();
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

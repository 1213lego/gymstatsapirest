package com.gymstatsapirest.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymstatsapirest.model.AutenticacionUsuario;
import com.gymstatsapirest.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetail implements UserDetails
{
    private String username;
    @JsonIgnore
    private String password;

    @JsonIgnore
    private Usuario usuario;


    private Collection<? extends  GrantedAuthority> authorities;

    public CustomUserDetail(AutenticacionUsuario autenticacionUsuario)
    {
        username=autenticacionUsuario.getUsername();
        password=autenticacionUsuario.getPassword();
        usuario=autenticacionUsuario.getUsuario();
        List<String> roles=new ArrayList<>();
        roles.add(usuario.getTipoUsuario().getTipo());
        authorities =roles.stream().map(rol ->
                new SimpleGrantedAuthority(rol)
        ).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetail that = (CustomUserDetail) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }
}

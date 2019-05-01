package com.gymstatsapirest.security.services;

import com.gymstatsapirest.model.AutenticacionUsuario;
import com.gymstatsapirest.repository.AutenticacionUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    AutenticacionUsuarioRepository autenticacionUsuarioRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        AutenticacionUsuario autenticacionUsuario=autenticacionUsuarioRepository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no econtrado con username : "+username));
        return new CustomUserDetail(autenticacionUsuario);
    }
}

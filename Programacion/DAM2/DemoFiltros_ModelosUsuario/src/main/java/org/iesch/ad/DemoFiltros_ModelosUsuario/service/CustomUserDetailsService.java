package org.iesch.ad.DemoFiltros_ModelosUsuario.service;

import org.iesch.ad.DemoFiltros_ModelosUsuario.modelo.UserEntity;
import org.iesch.ad.DemoFiltros_ModelosUsuario.repositorio.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityService.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userEntity.setLastLogin(LocalDateTime.now());

        userEntityRepository.save(userEntity);

        return userEntity; // Nueva forma.
    }
}

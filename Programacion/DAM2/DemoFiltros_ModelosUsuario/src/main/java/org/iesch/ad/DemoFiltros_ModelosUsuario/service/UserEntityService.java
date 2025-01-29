package org.iesch.ad.DemoFiltros_ModelosUsuario.service;

import org.iesch.ad.DemoFiltros_ModelosUsuario.modelo.UserEntity;
import org.iesch.ad.DemoFiltros_ModelosUsuario.repositorio.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {

    @Autowired
    UserEntityRepository userEntityRepository;

    public Optional<UserEntity> findByUserName(String username){
        return userEntityRepository.findByUsername(username);
    }
}

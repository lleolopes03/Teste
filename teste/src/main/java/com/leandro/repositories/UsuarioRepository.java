package com.leandro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.entities.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario,Long>{

}

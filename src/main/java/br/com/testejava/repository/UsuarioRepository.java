package br.com.testejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testejava.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>	{

}

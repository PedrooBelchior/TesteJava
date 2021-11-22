package br.com.testejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testejava.entity.NumerosEntity;

public interface NumerosRepository extends JpaRepository<NumerosEntity, Integer>	{
	
	NumerosEntity findByNumero(Integer numero);	
	

}


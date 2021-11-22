package br.com.testejava.service.impl;

import org.springframework.stereotype.Service;

import br.com.testejava.entity.NumerosEntity;
import br.com.testejava.repository.NumerosRepository;
import br.com.testejava.service.NumerosService;

@Service
public class NumerosServiceImpl implements NumerosService {
	
	private NumerosRepository repository;

	public NumerosServiceImpl(NumerosRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public NumerosEntity salvarNumeros(NumerosEntity numerosEntity) {
		return repository.save(numerosEntity);
	}

}

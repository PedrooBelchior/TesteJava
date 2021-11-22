package br.com.testejava.service.impl;

import org.springframework.stereotype.Service;

import br.com.testejava.entity.UsuarioEntity;
import br.com.testejava.exception.RegraNegocioException;
import br.com.testejava.repository.UsuarioRepository;
import br.com.testejava.service.UsuarioService;

@Service
public class UsuarioServiceImpl  implements UsuarioService{

	private UsuarioRepository repository;
	
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	public UsuarioEntity salvarUsuario(UsuarioEntity usuarioEntity) {
		
		if(usuarioEntity.getLogin() == null || usuarioEntity.getLogin() == "") {
			throw new RegraNegocioException("Por favor, insira um usuário.");
		}
		if(usuarioEntity.getNome() == null || usuarioEntity.getNome() == "") {
			throw new RegraNegocioException("Por favor, insira um nome.");
		}
		return repository.save(usuarioEntity);
	}

}

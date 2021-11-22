package br.com.testejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testejava.entity.UsuarioEntity;
import br.com.testejava.exception.RegraNegocioException;
import br.com.testejava.model.UsuarioDTO;
import br.com.testejava.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/api/usuarios")
@RequiredArgsConstructor
@Api(value = "Usuários")
public class UsuarioController {

	private final UsuarioService service;
	
	
	@PostMapping("/cadastrar")
	@ApiOperation("API 02 - Cadastrar usuário")
	public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO dto) {
		
		UsuarioEntity usuarioEntity = UsuarioEntity.builder().login(dto.getLogin()).nome(dto.getNome()).build();
		
		try {
			UsuarioEntity usuarioCadastrado = service.salvarUsuario(usuarioEntity);
			return new ResponseEntity("Usuário cadastrado com sucesso! \n" 
			+"Login: "+ usuarioCadastrado.getLogin()
			+"\n Nome: "+ usuarioCadastrado.getNome(),HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	
	
}

package br.com.testejava.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testejava.entity.NumerosEntity;
import br.com.testejava.exception.RegraNegocioException;
import br.com.testejava.model.NumerosDTO;
import br.com.testejava.service.NumerosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lista")
@RequiredArgsConstructor
@Api(value = "Listas")
public class ListaController {

	private final NumerosService service;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PostMapping("/cadastrar")
	@ApiOperation("API 01 - Cadastrar Lista")
	public ResponseEntity cadastrarLista(@RequestBody List<NumerosDTO> dto) {

		NumerosEntity numeroEntity = new NumerosEntity();
		List<NumerosEntity> numeroCadastrado = new ArrayList<>();
		NumerosEntity entidade = new NumerosEntity();


		if (dto.size() < 50) {

			for (NumerosDTO numerosDTO : dto) {
				try {
					numerosDTO.getNumeroInteiro();

					numeroEntity = NumerosEntity.builder().numero(numerosDTO.getNumeroInteiro()).build();

					entidade = service.salvarNumeros(numeroEntity);
					if (numeroCadastrado != null) {
						if (!numeroCadastrado.contains(entidade)) {
							numeroCadastrado.add(numeroEntity);

						} else {
							logger.log(Level.INFO, "Número já existente na lista.");
						}

					}

				} catch (RegraNegocioException e) {
					e.printStackTrace();
				}
			}

			return new ResponseEntity(numeroCadastrado, HttpStatus.CREATED);

		}
		return ResponseEntity.badRequest().body("Por favor insira uma lista com menos de 50 números.");
	}
	
	@PostMapping("/cadastrar-ordenar")
	@ApiOperation("API 05 - Cadastrar e Ordenar Lista de números ímpares")
	public ResponseEntity cadastrarListaOrdenada(@RequestBody List<NumerosDTO> dto) {

		NumerosEntity numeroEntity = new NumerosEntity();
		List<NumerosEntity> numeroCadastrado = new ArrayList<>();
		List<NumerosEntity> numeroCadastradoImpares = new ArrayList<>();
		NumerosEntity entidade = new NumerosEntity();

			for (NumerosDTO numerosDTO : dto) {
				try {
					numerosDTO.getNumeroInteiro();

					numeroEntity = NumerosEntity.builder().numero(numerosDTO.getNumeroInteiro()).build();
					
					entidade = service.salvarNumeros(numeroEntity);
					if (numeroCadastrado != null) {
						if (!(numeroEntity.getNumero() % 2 == 0)) {
							numeroCadastradoImpares.add(numeroEntity);
							
						} else {
							numeroCadastrado.add(numeroEntity);
						}

					}

				} catch (RegraNegocioException e) {
					e.printStackTrace();
				}
			}
			logger.log(Level.INFO, "Lista de números pares: " + numeroCadastrado);
			
			List<NumerosEntity> numerosOrdenados = this.ordenarPorPrioridade(numeroCadastradoImpares);

			return new ResponseEntity(numerosOrdenados, HttpStatus.CREATED);

	}
	
	private List<NumerosEntity> ordenarPorPrioridade(List<NumerosEntity> reservas) {
		return Optional.ofNullable(reservas).orElseGet(Collections::emptyList).stream()				
		.sorted(Comparator.comparing(NumerosEntity::getNumero))
		.collect(Collectors.toList());
	}
	
}



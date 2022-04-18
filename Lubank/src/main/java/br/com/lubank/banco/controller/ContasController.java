package br.com.lubank.banco.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lubank.banco.controller.dto.ContaDto;
import br.com.lubank.banco.controller.dto.DetalhesDaContaDto;
import br.com.lubank.banco.controller.form.AtualizacaoContaForm;
import br.com.lubank.banco.controller.form.ContaForm;
import br.com.lubank.banco.modelo.Conta;
import br.com.lubank.banco.repository.ContaRepository;
import br.com.lubank.banco.repository.ContaRepositoryAtualizacao;

@RestController
@RequestMapping("/contas")
public class ContasController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaRepositoryAtualizacao contaRepositoryAtualizacao;
	
	@GetMapping
	@Cacheable(value = "listaDeContas")
	public Page<ContaDto> lista(@RequestParam(required = false) String tipoDeConta, 
			@PageableDefault(sort = "tipoDeConta", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) throws Exception {
		
		Page<Conta> contas = contaRepository.findAll(paginacao);
		return ContaDto.converter(contas);
	}
	
	@PostMapping("/contacorrente")
	@Transactional
	@CacheEvict(value = "listaDeContas", allEntries = true)
	public ResponseEntity<ContaDto> cadastrarContaCorrente(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
		Conta conta = form.converterContaCorrente();
		contaRepository.save(conta);
		
		URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}
	
	@PostMapping("/contapoupanca")
	@Transactional
	@CacheEvict(value = "listaDeContas", allEntries = true)
	public ResponseEntity<ContaDto> cadastrarContaPoupanca(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
		Conta conta = form.converterContaPoupanca();
		contaRepository.save(conta);
		
		URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<DetalhesDaContaDto> detalhar(@PathVariable int numero) {
		Optional<Conta> conta = contaRepository.findByNumero(numero);
		if (conta.isPresent()) {
			return ResponseEntity.ok(new DetalhesDaContaDto(conta.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{numero}")
	@Transactional
	@CacheEvict(value = "listaDeContas", allEntries = true)
	public ResponseEntity<ContaDto> atualizar(@PathVariable int numero, @RequestBody @Valid AtualizacaoContaForm form) {
		Optional<Conta> conta = contaRepository.findByNumero(numero);
		if (conta.isPresent()) {			
			Conta contaAtualizada = form.atualizar(numero, contaRepositoryAtualizacao);
			return ResponseEntity.ok(new ContaDto(contaAtualizada));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{numero}")
	@Transactional
	@CacheEvict(value = "listaDeContas", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable int numero) {
		Optional<Conta> conta = contaRepository.findByNumero(numero);
		if (conta.isPresent()) {
			contaRepository.deleteByNumero(numero);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
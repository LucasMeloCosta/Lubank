package br.com.lubank.banco.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lubank.banco.modelo.Conta;
@Repository
public interface ContaRepositoryAtualizacao extends JpaRepository<Conta, Long> {

	Page<Conta> findByTipoDeConta(String tipoDeConta, Pageable paginacao);
	
	Conta findByNumero(int numero);

}

package br.com.lubank.banco.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lubank.banco.modelo.Conta;
@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Page<Conta> findByTipoDeConta(String tipoDeConta, Pageable paginacao);
	
	Optional<Conta> findByNumero(int numero);
	
	void deleteByNumero(int numero);

}

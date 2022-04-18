package br.com.lubank.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lubank.banco.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}

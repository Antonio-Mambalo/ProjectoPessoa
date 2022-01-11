package mz.co.ubisse.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.co.ubisse.app.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}

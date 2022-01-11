package mz.co.ubisse.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import mz.co.ubisse.app.model.Pessoa;
import mz.co.ubisse.app.repository.PessoaRepository;

@RestController       
public class PessoaController {
	
	@Autowired
	public PessoaRepository repository;
	
	@GetMapping(path = "/pessoas/{id}")
	public ResponseEntity consultar(@PathVariable("id") Long id) {
	return repository.findById(id)
			.map(record -> ResponseEntity.ok().body(record ))
			.orElse(ResponseEntity.notFound().build());
	
	}
	
	@GetMapping(path = "/pessoas")
	public List<Pessoa> listar(){
		return repository.findAll();
	}
	
	@PostMapping(path = "/pessoas/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa salvar(Pessoa pessoa) {
		return repository.save(pessoa);
	}
	
	 @DeleteMapping("/pessoas/{id}")
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public void deletar (@PathVariable long id) {
		 repository.findById(id).map(pessoa -> {
			 repository.delete(pessoa);
			 return Void.TYPE;
		 }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	 }
	 
	 @PutMapping("/pessoas/{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void atualisarPessoa (@PathVariable long id, @RequestBody Pessoa pessoaAtualizada) {
		 repository.findById(id).map(pessoa -> {
			 pessoaAtualizada.setId(pessoa.getId());
			 return repository.save(pessoaAtualizada);
		 })
		 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	 }
	 
}

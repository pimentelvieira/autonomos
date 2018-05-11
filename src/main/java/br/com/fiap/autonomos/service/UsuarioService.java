package br.com.fiap.autonomos.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.autonomos.model.Usuario;
import br.com.fiap.autonomos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario salvar(Usuario u) {
		return repository.save(u);
	}
	
	public Iterable<Usuario> listarTodos() {
		return repository.findAll();
	}
	
	public List<Usuario> findByNome(String nome) {
		return repository.findByNome(nome);
	}
	
	public Usuario findById(Integer id) {
		return repository.findById(id).get();
	}
	
	@PostConstruct
	public void adicionaUsuarios() {
		this.salvar(new Usuario("William", "Rua Miguel Jorge", "Avenida Brigadeiro Faria Lima"));
		this.salvar(new Usuario("Fernanda", "Rua Alcides Luizetto", "Avenida Paulista"));
		this.salvar(new Usuario("Luis", "Rua Tereza Aragão", "Brás"));
	}
}

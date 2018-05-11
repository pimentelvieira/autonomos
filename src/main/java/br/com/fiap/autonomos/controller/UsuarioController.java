package br.com.fiap.autonomos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.autonomos.model.Usuario;
import br.com.fiap.autonomos.model.Veiculo;
import br.com.fiap.autonomos.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VeiculoController veiculoController;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario u) {
		return ResponseEntity.ok(usuarioService.salvar(u));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Iterable<Usuario>> listarTodos() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(usuarioService.findById(id));
	}
	
	@RequestMapping(value="/{id}/veiculos/disponiveis/selecionar", method=RequestMethod.GET)
	public ResponseEntity<Veiculo> selecionarVeiculoDisponivel(@PathVariable("id") Integer id) throws InterruptedException {
		Usuario usuario = usuarioService.findById(id);
		Veiculo veiculoSelecionado = veiculoController.selecionarDisponivel();
		//Thread.sleep(30000);
		veiculoSelecionado.emViagem();
		veiculoSelecionado.setLocalizacao(usuario.getOrigem());
		veiculoController.salvar(veiculoSelecionado);
		
		//Thread.sleep(30000);
		
		veiculoSelecionado.setLocalizacao(usuario.getDestino());
		veiculoSelecionado.disponivel();
		
		return veiculoController.salvar(veiculoSelecionado);
	}
}

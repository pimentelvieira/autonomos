package br.com.fiap.autonomos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.autonomos.model.Status;
import br.com.fiap.autonomos.model.Veiculo;
import br.com.fiap.autonomos.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo v) {
		return ResponseEntity.ok(veiculoService.salvar(v));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Iterable<Veiculo>> listarTodos() {
		return ResponseEntity.ok(veiculoService.listarTodos());
	}
	
	@RequestMapping(value="/disponiveis", method=RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarDisponiveis() {
		return ResponseEntity.ok(veiculoService.findByStatus(Status.DISPONIVEL));
	}
	
	public Veiculo selecionarDisponivel() {
		Veiculo veiculoDisponivel = veiculoService.findByStatus(Status.DISPONIVEL).stream().findAny().get();
		veiculoDisponivel.setStatus(Status.TRANSITO);
		veiculoService.salvar(veiculoDisponivel);
		return veiculoDisponivel;
	}
	
	@RequestMapping(value="/indisponiveis", method=RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarIndisponiveis() {
		return ResponseEntity.ok(veiculoService.findByStatus(Status.INDISPONIVEL));
	}
	
	@RequestMapping(value="/viagem", method=RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarEmViagem() {
		return ResponseEntity.ok(veiculoService.findByStatus(Status.VIAGEM));
	}
	
	@RequestMapping(value="/transito", method=RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarEmTransito() {
		return ResponseEntity.ok(veiculoService.findByStatus(Status.TRANSITO));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Veiculo> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(veiculoService.findById(id));
	}
}

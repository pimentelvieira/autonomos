package br.com.fiap.autonomos.controller;

import java.util.List;

import br.com.fiap.autonomos.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation(
			value="Cadastrar novo veículo",
			response=Veiculo.class,
			notes="Essa operação salva um novo registro com as informações do veículo.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna o veículo gravado",
					response=Veiculo.class
			)

	})
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo v) {
		return ResponseEntity.ok(veiculoService.salvar(v));
	}

	@ApiOperation(
			value="Listar todos os veículos",
			response=Veiculo.class,
			notes="Essa operação retorna todos os veículos gravados.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna todos os veículos gravados",
					response=Veiculo.class
			)

	})
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Iterable<Veiculo>> listarTodos() {
		return ResponseEntity.ok(veiculoService.listarTodos());
	}

	@ApiOperation(
			value="Listar todos os veículos disponíveis",
			response=Veiculo.class,
			notes="Essa operação retorna todos os veículos disponíveis.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna todos os veículos disponíveis",
					response=Veiculo.class
			)

	})
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

	@ApiOperation(
			value="Listar todos os veículos indisponíveis",
			response=Veiculo.class,
			notes="Essa operação retorna todos os veículos indisponíveis.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna todos os veículos indisponíveis",
					response=Veiculo.class
			)

	})
	@RequestMapping(value="/indisponiveis", method=RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarIndisponiveis() {
		return ResponseEntity.ok(veiculoService.findByStatus(Status.INDISPONIVEL));
	}

	@ApiOperation(
			value="Listar todos os veículos em viagem",
			response=Veiculo.class,
			notes="Essa operação retorna todos os veículos em viagem.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna todos os veículos em viagem",
					response=Veiculo.class
			)

	})
	@RequestMapping(value="/viagem", method=RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarEmViagem() {
		return ResponseEntity.ok(veiculoService.findByStatus(Status.VIAGEM));
	}

	@ApiOperation(
			value="Listar todos os veículos em trânsito para o endereço do usuário",
			response=Veiculo.class,
			notes="Essa operação retorna todos os veículos em trânsito para o endereço do usuário.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna todos os veículos em trânsito para o endereço do usuário",
					response=Veiculo.class
			)

	})
	@RequestMapping(value="/transito", method=RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarEmTransito() {
		return ResponseEntity.ok(veiculoService.findByStatus(Status.TRANSITO));
	}

	@ApiOperation(
			value="Retornar um veículo a partir do ID",
			response=Veiculo.class,
			notes="Essa operação retorna um veículo a partir do ID.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna um veículo a partir do ID",
					response=Veiculo.class
			)

	})
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Veiculo> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(veiculoService.findById(id));
	}
}

package br.com.fiap.autonomos.controller;

import br.com.fiap.autonomos.model.Status;
import br.com.fiap.autonomos.service.VeiculoService;
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

import br.com.fiap.autonomos.model.Usuario;
import br.com.fiap.autonomos.model.Veiculo;
import br.com.fiap.autonomos.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private VeiculoController veiculoController;

	@ApiOperation(
			value="Cadastrar novo usuário",
			response=Veiculo.class,
			notes="Essa operação salva um novo registro com as informações do usuário.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna o usuário gravado",
					response=Usuario.class
			)

	})
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario u) {
		return ResponseEntity.ok(usuarioService.salvar(u));
	}

	@ApiOperation(
			value="Listar todos os usuários",
			response=Veiculo.class,
			notes="Essa operação retorna todos os usuários gravados.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna todos os usuários gravados",
					response=Usuario.class
			)

	})
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Iterable<Usuario>> listarTodos() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	@ApiOperation(
			value="Retornar um usuário a partir do ID",
			response=Veiculo.class,
			notes="Essa operação retorna um usuário a partir do ID.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna um usuário a partir do ID",
					response=Usuario.class
			)

	})
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(usuarioService.findById(id));
	}

	@ApiOperation(
			value="Selecionar um veículo disponível",
			response=Veiculo.class,
			notes="Essa operação seleciona um veículo entre os disponíveis.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Retorna um veículo disponível",
					response=Veiculo.class
			)

	})
	@RequestMapping(value="/{id}/veiculos/disponiveis/selecionar", method=RequestMethod.GET)
	public ResponseEntity<Veiculo> selecionarVeiculoDisponivel(@PathVariable("id") Integer id) throws InterruptedException {
		Usuario usuario = usuarioService.findById(id);
		Veiculo veiculoSelecionado = veiculoController.selecionarDisponivel();

		veiculoSelecionado.emTransito();
		veiculoSelecionado.setLocalizacao(usuario.getOrigem());
		
		return veiculoController.salvar(veiculoSelecionado);
	}

	@ApiOperation(
			value="Iniciar a viagem",
			response=Veiculo.class,
			notes="Essa operação inicia uma viagem.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Inicia a viagem",
					response=Veiculo.class
			)

	})
	@RequestMapping(value="/{id}/iniciarViagem/{carId}", method=RequestMethod.GET)
	public ResponseEntity<Veiculo> iniciarViagems(@PathVariable("id") Integer id,@PathVariable("carId") Integer carId) {

		Veiculo veiculo = veiculoService.findById(carId);

		veiculo.setStatus(Status.VIAGEM);
		veiculo.setLocalizacao("");

		veiculoService.salvar(veiculo);
		return ResponseEntity.ok(veiculo);
	}

	@ApiOperation(
			value="Finalizar a viagem",
			response=Veiculo.class,
			notes="Essa operação finaliza uma viagem.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="Finaliza a viagem",
					response=Veiculo.class
			)

	})
	@RequestMapping(value="/{id}/finalizarViagem/{carId}", method=RequestMethod.GET)
	public ResponseEntity<Veiculo> finalizarViagems(@PathVariable("id") Integer id, @PathVariable("carId") Integer carId) {
		Usuario usuario = usuarioService.findById(id);
		Veiculo veiculo = veiculoService.findById(carId);

		veiculo.setStatus(Status.DISPONIVEL);
		veiculo.setLocalizacao(usuario.getDestino());

		veiculoService.salvar(veiculo);
		return ResponseEntity.ok(veiculo);

	}
}

package br.com.fiap.autonomos.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.autonomos.model.Status;
import br.com.fiap.autonomos.model.Veiculo;
import br.com.fiap.autonomos.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;
	
	public Veiculo salvar(Veiculo v) {
		return repository.save(v);
	}
	
	public Iterable<Veiculo> listarTodos() {
		return repository.findAll();
	}
	
	public List<Veiculo> findByStatus(Status status) {
		return repository.findByStatus(status);
	}
	
	public Veiculo findById(Integer id) {
		return repository.findById(id).get();
	}
	
	@PostConstruct
	public void adicionaVeiculos() {
		this.salvar(new Veiculo("JBG5411", "Wolkswagen Gol", Status.DISPONIVEL, "Avenida Brigadeiro Faria Lima"));
		this.salvar(new Veiculo("JJH9087", "Fiat Pálio", Status.DISPONIVEL, "Avenida Paulista"));
		this.salvar(new Veiculo("BHG7765", "Renault Sandero", Status.INDISPONIVEL, "Avenida Jabaquara"));
		this.salvar(new Veiculo("BNM9087", "Wolkswagen Golf", Status.VIAGEM, "Rua Augusta"));
		this.salvar(new Veiculo("LCX0167", "Toyota Corolla", Status.DISPONIVEL, "Rua Formosa"));
		this.salvar(new Veiculo("HVC7623", "GM Celta", Status.INDISPONIVEL, "Brás"));
		this.salvar(new Veiculo("OPK0087", "GM Camaro", Status.DISPONIVEL, "Shopping Taboão"));
	}
}

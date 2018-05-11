package br.com.fiap.autonomos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.autonomos.model.Status;
import br.com.fiap.autonomos.model.Veiculo;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {

	List<Veiculo> findByStatus(Status status);
	List<Veiculo> findByLocalizacao(String localizacao);
	Veiculo findByPlaca(String placa);
}

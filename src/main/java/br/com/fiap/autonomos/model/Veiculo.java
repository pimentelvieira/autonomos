package br.com.fiap.autonomos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String placa;
	private String modelo;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String localizacao;
	
	public Veiculo(String placa, String modelo, Status status, String localizacao) {
		this.placa = placa;
		this.modelo = modelo;
		this.status = status;
		this.localizacao = localizacao;
	}
	
	public Veiculo() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localizacao == null) {
			if (other.localizacao != null)
				return false;
		} else if (!localizacao.equals(other.localizacao))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	public void emViagem() {
		this.setStatus(Status.VIAGEM);
	}
	public void emTransito() {
		this.setStatus(Status.TRANSITO);
	}
	public void disponivel() {
		this.setStatus(Status.DISPONIVEL);
	}
	public void indisponivel() {
		this.setStatus(Status.INDISPONIVEL);
	}
}

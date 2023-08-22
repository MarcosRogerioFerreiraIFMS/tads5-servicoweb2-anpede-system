package br.com.anpede.dto;

import java.io.Serializable;

import br.com.anpede.entities.Equipamento;
import br.com.anpede.entities.EquipamentoItem;
import br.com.anpede.entities.enums.Situacao;

public class EquipamentoItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numeroSerie;
	private Situacao situação;
	private Equipamento equipameto;
	
	public EquipamentoItemDTO() {
		// TODO Auto-generated constructor stub
	}

	public EquipamentoItemDTO(Long id, String numeroSerie, Situacao situação, Equipamento equipameto) {
		this.id = id;
		this.numeroSerie = numeroSerie;
		this.situação = situação;
		this.equipameto = equipameto;
	}
	
	public EquipamentoItemDTO(EquipamentoItem entity) {
		this.id = entity.getId();
		this.numeroSerie = entity.getNumeroSerie();
		this.situação = entity.getSituacao();
		this.equipameto = entity.getEquipamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Situacao getSituação() {
		return situação;
	}

	public void setSituação(Situacao situação) {
		this.situação = situação;
	}

	public Equipamento getEquipameto() {
		return equipameto;
	}

	public void setEquipameto(Equipamento equipameto) {
		this.equipameto = equipameto;
	}
}

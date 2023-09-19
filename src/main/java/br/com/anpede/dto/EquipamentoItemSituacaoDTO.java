package br.com.anpede.dto;

import java.io.Serializable;

public class EquipamentoItemSituacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numeroSerie;
	
	public EquipamentoItemSituacaoDTO() { }

	public EquipamentoItemSituacaoDTO(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

}

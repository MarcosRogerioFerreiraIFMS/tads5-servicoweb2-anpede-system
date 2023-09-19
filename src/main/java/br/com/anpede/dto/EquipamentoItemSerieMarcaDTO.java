package br.com.anpede.dto;

import java.io.Serializable;

public class EquipamentoItemSerieMarcaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numeroSerie;
	private String marca;
	
	public EquipamentoItemSerieMarcaDTO() { }

	public EquipamentoItemSerieMarcaDTO(String numeroSerie, String marca) {
		this.numeroSerie = numeroSerie;
		this.marca = marca;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}

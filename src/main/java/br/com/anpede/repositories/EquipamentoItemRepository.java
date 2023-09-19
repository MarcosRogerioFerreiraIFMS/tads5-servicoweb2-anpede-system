package br.com.anpede.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.anpede.dto.EquipamentoItemSerieMarcaDTO;
import br.com.anpede.dto.EquipamentoItemSituacaoDTO;
import br.com.anpede.entities.EquipamentoItem;
import br.com.anpede.entities.enums.Situacao;

@Repository
public interface EquipamentoItemRepository extends JpaRepository<EquipamentoItem, Long> {

	//QueryMethods
	EquipamentoItem findByNumeroSerie(String numeroSerie);
	List<EquipamentoItem> findBySituacao(Situacao situacao);
	
	//@Query - Consultas customizadas ==================================
	
	//SELECT numero_serie FROM tb_equipamento_item WHERE situacao = 'DISPONIVEL'
	@Query("SELECT new br.com.anpede.dto.EquipamentoItemSituacaoDTO(obj.numeroSerie) "
			+ "FROM EquipamentoItem obj WHERE obj.situacao = :situacao")
	List<EquipamentoItemSituacaoDTO> findBySituacaoSQL(Situacao situacao);
	
	
	//SELECT ei.numero_serie, e.marca FROM tb_equipamento_item ei, tb_equipamento e WHERE ei.id_equipamento_fk = e.id AND e.marca = 'Dellamed'
	@Query("SELECT new br.com.anpede.dto.EquipamentoItemSerieMarcaDTO("
			+ "obj.numeroSerie, obj.equipamento.marca) "
			+ "FROM EquipamentoItem obj "
			+ "WHERE obj.equipamento.marca = :marca")
	List<EquipamentoItemSerieMarcaDTO> findBySerieMarcaSQL(String marca);
	
	
}













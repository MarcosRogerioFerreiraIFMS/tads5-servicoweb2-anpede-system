package br.com.anpede.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.anpede.dto.EquipamentoItemDTO;
import br.com.anpede.dto.EquipamentoItemSerieMarcaDTO;
import br.com.anpede.dto.EquipamentoItemSituacaoDTO;
import br.com.anpede.entities.EquipamentoItem;
import br.com.anpede.entities.enums.Situacao;
import br.com.anpede.repositories.EquipamentoItemRepository;
import br.com.anpede.services.exceptions.DataBaseException;
import br.com.anpede.services.exceptions.ResourceNotFoundException;

@Service
public class EquipamentoItemService {
	
	@Autowired
	private EquipamentoItemRepository repository;

	@Transactional(readOnly = true)
	public List<EquipamentoItemDTO> findAll(){
		List<EquipamentoItem> lista = repository.findAll();
		return lista.stream().map(x -> new EquipamentoItemDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public EquipamentoItemDTO findById(Long id){
		Optional<EquipamentoItem> obj = repository.findById(id);
		EquipamentoItem entity = obj.orElseThrow(() -> new ResourceNotFoundException("O registro não foi localizado na base de dados"));
		return new EquipamentoItemDTO(entity);
	}
	
	// START - Query Methods=====================================================
	@Transactional(readOnly = true)
	public EquipamentoItemDTO findByNumeroSerie(String numeroSerie){
		EquipamentoItem obj = repository.findByNumeroSerie(numeroSerie);
		return new EquipamentoItemDTO(obj);
	}
	
	@Transactional(readOnly = true)
	public List<EquipamentoItemDTO> findBySituacao(Situacao situacao){
		List<EquipamentoItem> lista = repository.findBySituacao(situacao);
		return lista.stream().map(x -> new EquipamentoItemDTO(x)).collect(Collectors.toList());
	}
	// END - Query Methods=====================================================
	
	// START - @Query - Consultas customizadas=================================
	@Transactional(readOnly = true)
	public List<EquipamentoItemSituacaoDTO> findBySituacaoSQL(Situacao situacao){
		return repository.findBySituacaoSQL(situacao);
	}
	
	@Transactional(readOnly = true)
	public List<EquipamentoItemSerieMarcaDTO> findBySerieMarcaSQL(String marca){
		return repository.findBySerieMarcaSQL(marca);
	}
	// END - @Query - Consultas customizadas===================================
	
	@Transactional
	public EquipamentoItemDTO insert(EquipamentoItemDTO dto) {
		EquipamentoItem entity = new EquipamentoItem();		
		converterEntityToDTO(entity, dto);				
		entity = repository.save(entity);
		return new EquipamentoItemDTO(entity);
	}
	
	@Transactional
	public EquipamentoItemDTO update(Long id, EquipamentoItemDTO dto) {
		try {
			EquipamentoItem entity = repository.getReferenceById(id);
			
			converterEntityToDTO(entity, dto);
			
			entity = repository.save(entity);
			return new EquipamentoItemDTO(entity);
		} catch (jakarta.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("O recurso com o ID "+id+" não foi localizado");
		}
	}
	
	private void converterEntityToDTO(EquipamentoItem entity, EquipamentoItemDTO dto) {
		entity.setNumeroSerie(dto.getNumeroSerie());
		entity.setSituacao(dto.getSituacao());
		entity.setEquipamento(dto.getEquipamento());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de Integridade");
		}			
	}
}










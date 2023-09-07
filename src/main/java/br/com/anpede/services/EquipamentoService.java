package br.com.anpede.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.anpede.dto.EquipamentoDTO;
import br.com.anpede.entities.Equipamento;
import br.com.anpede.repositories.EquipamentoRepository;
import br.com.anpede.services.exceptions.DataBaseException;
import br.com.anpede.services.exceptions.ResourceNotFoundException;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository repository;

	@Transactional(readOnly = true)
	public List<EquipamentoDTO> findAll(){
		List<Equipamento> lista = repository.findAll();
		return lista.stream().map(x -> new EquipamentoDTO(x, x.getEquipamentosItem())).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public EquipamentoDTO findById(Long id){
		Optional<Equipamento> obj = repository.findById(id);
		Equipamento entity = obj.orElseThrow(() -> new ResourceNotFoundException("O registro não foi localizado na base de dados"));
		return new EquipamentoDTO(entity, entity.getEquipamentosItem());
	}
	
	@Transactional
	public EquipamentoDTO insert(EquipamentoDTO dto) {
		Equipamento entity = new Equipamento();		
		converterEntityToDTO(entity, dto);				
		entity = repository.save(entity);
		return new EquipamentoDTO(entity);
	}
	
	@Transactional
	public EquipamentoDTO update(Long id, EquipamentoDTO dto) {
		try {
			Equipamento entity = repository.getReferenceById(id);
			
			converterEntityToDTO(entity, dto);
			
			entity = repository.save(entity);
			return new EquipamentoDTO(entity);
		} catch (jakarta.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("O recurso com o ID "+id+" não foi localizado");
		}
	}
	
	private void converterEntityToDTO(Equipamento entity, EquipamentoDTO dto) {
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setMarca(dto.getMarca());
		entity.setCategoria(dto.getCategoria());
		entity.setFoto(dto.getFoto());
		entity.setValor(dto.getValor());
		entity.setQuantidade(dto.getQuantidade());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de Integridade");
		}			
	}
}










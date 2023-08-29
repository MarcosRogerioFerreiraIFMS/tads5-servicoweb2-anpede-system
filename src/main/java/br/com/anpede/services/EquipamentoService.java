package br.com.anpede.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.anpede.dto.EquipamentoDTO;
import br.com.anpede.entities.Equipamento;
import br.com.anpede.repositories.EquipamentoRepository;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository repository;

	@Transactional(readOnly = true)
	public List<EquipamentoDTO> findAll(){
		List<Equipamento> lista = repository.findAll();
		return lista.stream().map(x -> new EquipamentoDTO(x, x.getEquipamentosItem())).collect(Collectors.toList());
	}
	
	/*
	@Transactional(readOnly = true)
	public AssociadoDTO findById(Long id) {
		Optional<Associado> obj = repository.findById(id);
		
		Associado entity = obj.orElseThrow(() -> new ResourceNotFoundException("O registro solicitado não foi localizado."));
		return new AssociadoDTO(entity);		
	}

	@Transactional
	public AssociadoDTO insert(AssociadoDTO dto) {
		Associado entity = new Associado();
		entity.setNome(dto.getNome());
		entity.setCPF(dto.getCPF());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setTelefone(dto.getTelefone());
		entity.setEmail(dto.getEmail());
		entity.setEndereco(dto.getEndereco());
		
		entity = repository.save(entity);

		return new AssociadoDTO(entity);
	}

	@Transactional
	public AssociadoDTO update(Long id, AssociadoDTO dto) {
		
		try {
			Associado entity = repository.getReferenceById(id);
			
			entity.setNome(dto.getNome());
			entity.setCPF(dto.getCPF());
			entity.setDataNascimento(dto.getDataNascimento());
			entity.setTelefone(dto.getTelefone());
			entity.setEmail(dto.getEmail());
			entity.setEndereco(dto.getEndereco());
			
			entity = repository.save(entity);
	
			return new AssociadoDTO(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(
					"O recurso com o ID "+id+" não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
		if(repository.existsById(id)) {
			repository.deleteById(id);
		}
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("O registro solicitado não foi localizado.");
		}
	}
	*/
}










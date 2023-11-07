package br.com.anpede.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.anpede.dto.AssociadoDTO;
import br.com.anpede.dto.AssociadoInsertDTO;
import br.com.anpede.dto.AssociadoUpdateDTO;
import br.com.anpede.dto.RoleDTO;
import br.com.anpede.entities.Associado;
import br.com.anpede.entities.Role;
import br.com.anpede.repositories.AssociadoRepository;
import br.com.anpede.repositories.RoleRepository;
import br.com.anpede.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public List<AssociadoDTO> findAll(){
		List<Associado> lista = repository.findAll();
		return lista.stream().map(x -> new AssociadoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public AssociadoDTO findById(Long id) {
		Optional<Associado> obj = repository.findById(id);
		
		Associado entity = obj.orElseThrow(() -> new ResourceNotFoundException("O registro solicitado não foi localizado."));
		return new AssociadoDTO(entity);		
	}

	@Transactional
	public AssociadoDTO insert(AssociadoInsertDTO dto) {
		Associado entity = new Associado();
		copiarDTOParaEntidade(dto, entity);	
		
		entity.setSenha(passwordEncoder.encode(dto.getSenha()));
		
		entity = repository.save(entity);
		return new AssociadoDTO(entity);
	}

	@Transactional
	public AssociadoDTO update(Long id, AssociadoUpdateDTO dto) {
		try {
			Associado entity = repository.getReferenceById(id);
			copiarDTOParaEntidade(dto, entity);						
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
	
	private void copiarDTOParaEntidade(AssociadoDTO dto, Associado entity) {
		entity.setNome(dto.getNome());
		entity.setCPF(dto.getCPF());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setTelefone(dto.getTelefone());
		entity.setEmail(dto.getEmail());
		entity.setEndereco(dto.getEndereco());
		
		entity.getRoles().clear();
		for(RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getReferenceById(roleDTO.getId());
			entity.getRoles().add(role);
		}
	}
}










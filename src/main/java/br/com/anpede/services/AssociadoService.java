package br.com.anpede.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.anpede.dto.AssociadoDTO;
import br.com.anpede.entities.Associado;
import br.com.anpede.repositories.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository repository;

	@Transactional(readOnly = true)
	public List<AssociadoDTO> findAll(){
		List<Associado> lista = repository.findAll();
		return lista.stream().map(x -> new AssociadoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public AssociadoDTO findById(Long id) {
		Optional<Associado> obj = repository.findById(id);
		Associado entity = obj.get();
		return new AssociadoDTO(entity);		
	}
}




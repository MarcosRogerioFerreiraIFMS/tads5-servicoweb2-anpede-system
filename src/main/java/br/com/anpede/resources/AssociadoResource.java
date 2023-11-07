package br.com.anpede.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.anpede.dto.AssociadoDTO;
import br.com.anpede.dto.AssociadoInsertDTO;
import br.com.anpede.dto.AssociadoUpdateDTO;
import br.com.anpede.entities.Associado;
import br.com.anpede.services.AssociadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/associados")
public class AssociadoResource {
	
	@Autowired
	private AssociadoService service;
	
	@GetMapping
	public ResponseEntity<List<AssociadoDTO>> findAll() {
		List<AssociadoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id){
		AssociadoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<AssociadoDTO> insert(@Valid @RequestBody AssociadoInsertDTO dto){
		AssociadoDTO associdadoDTO = service.insert(dto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(associdadoDTO.getId())
					.toUri();
		return ResponseEntity.created(uri).body(associdadoDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AssociadoDTO> update(
			@PathVariable Long id,
			@Valid @RequestBody AssociadoUpdateDTO dto){
		AssociadoDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}











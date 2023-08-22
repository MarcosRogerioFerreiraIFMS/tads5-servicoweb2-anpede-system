package br.com.anpede.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anpede.dto.EquipamentoItemDTO;
import br.com.anpede.services.EquipamentoItemService;

@RestController
@RequestMapping(value = "/equipamentos_item")
public class EquipamentoItemResource {
	
	@Autowired
	private EquipamentoItemService service;
	
	@GetMapping
	public ResponseEntity<List<EquipamentoItemDTO>> findAll() {
		List<EquipamentoItemDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	/*
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id){
		AssociadoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<AssociadoDTO> insert(@RequestBody AssociadoDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(dto.getId())
					.toUri();
		return ResponseEntity.created(uri).body(null);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AssociadoDTO> update(
			@PathVariable Long id,
			@RequestBody AssociadoDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	*/

}











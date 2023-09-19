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

import br.com.anpede.dto.EquipamentoItemDTO;
import br.com.anpede.dto.EquipamentoItemSerieMarcaDTO;
import br.com.anpede.dto.EquipamentoItemSituacaoDTO;
import br.com.anpede.entities.enums.Situacao;
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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipamentoItemDTO> findById(@PathVariable Long id){		
		EquipamentoItemDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);	
	}
	
	// START - Query Methods=====================================================
	@GetMapping(value = "/serie/{numeroSerie}")
	public ResponseEntity<EquipamentoItemDTO> findByNumeroSerie(@PathVariable String numeroSerie){
		EquipamentoItemDTO dto = service.findByNumeroSerie(numeroSerie);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/situacao/{situacao}")
	public ResponseEntity<List<EquipamentoItemDTO>> findBySituacao(@PathVariable Situacao situacao){
		List<EquipamentoItemDTO> lista = service.findBySituacao(situacao);
		return ResponseEntity.ok().body(lista);
	}
	// END - Query Methods=====================================================
	
	// START - @Query - Consultas Customizadas=================================
	@GetMapping(value = "/situacaosql/{situacao}")
	public ResponseEntity<List<EquipamentoItemSituacaoDTO>> findBySituacaoSQL(@PathVariable Situacao situacao){
		List<EquipamentoItemSituacaoDTO> lista = service.findBySituacaoSQL(situacao);
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/seriemarcasql/{marca}")
	public ResponseEntity<List<EquipamentoItemSerieMarcaDTO>> findBySerieMarcaSQL(@PathVariable String marca){
		List<EquipamentoItemSerieMarcaDTO> lista = service.findBySerieMarcaSQL(marca);
		return ResponseEntity.ok().body(lista);
	}
	// END - @Query - Consultas Customizadas===================================
	
	@PostMapping
	public ResponseEntity<EquipamentoItemDTO> insert(@RequestBody EquipamentoItemDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(dto.getId())
					.toUri();
		return ResponseEntity.created(uri).body(null);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EquipamentoItemDTO> update(@PathVariable Long id, @RequestBody EquipamentoItemDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}











package br.com.anpede.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.anpede.dto.AssociadoInsertDTO;
import br.com.anpede.entities.Associado;
import br.com.anpede.repositories.AssociadoRepository;
import br.com.anpede.resources.exceptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AssociadoInsertValidator implements ConstraintValidator<AssociadoInsertValid, AssociadoInsertDTO> {
	
	@Autowired
	private AssociadoRepository repository;
	
	@Override
	public void initialize(AssociadoInsertValid ann) {
	}

	@Override
	public boolean isValid(AssociadoInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		Associado associado = repository.findByEmail(dto.getEmail());
		
		if(associado != null) {
			list.add(new FieldMessage("email", "O Email informado já existe no sistema"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

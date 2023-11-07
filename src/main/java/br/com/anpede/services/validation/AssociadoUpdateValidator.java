package br.com.anpede.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.anpede.dto.AssociadoInsertDTO;
import br.com.anpede.dto.AssociadoUpdateDTO;
import br.com.anpede.entities.Associado;
import br.com.anpede.repositories.AssociadoRepository;
import br.com.anpede.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AssociadoUpdateValidator implements ConstraintValidator<AssociadoUpdateValid, AssociadoUpdateDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AssociadoRepository repository;
	
	@Override
	public void initialize(AssociadoUpdateValid ann) {
	}

	@Override
	public boolean isValid(AssociadoUpdateDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long associadoId = Long.parseLong(uriVars.get("id"));
		
		Associado associado = repository.findByEmail(dto.getEmail());
		
		if(associado != null && associadoId != associado.getId()) {
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

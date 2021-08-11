package com.daniloperez.academia.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.dto.InstrutorDTO;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.resources.exceptions.FieldMessage;

public class InstrutorUpdateValidator implements ConstraintValidator<InstrutorUpdate, InstrutorDTO> {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private InstrutorRepository repo;
	
	@Override
	public void initialize(InstrutorUpdate ann) {
	}

	@Override
	public boolean isValid(InstrutorDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		Instrutor aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
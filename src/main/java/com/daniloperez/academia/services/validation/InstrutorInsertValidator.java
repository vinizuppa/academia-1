package com.daniloperez.academia.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.dto.InstrutorNewDTO;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.resources.exceptions.FieldMessage;

public class InstrutorInsertValidator implements ConstraintValidator<InstrutorInsert, InstrutorNewDTO> {
	@Autowired
	private InstrutorRepository repo;
	
	@Override
	public void initialize(InstrutorInsert ann) {
	}

	@Override
	public boolean isValid(InstrutorNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		Instrutor aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
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
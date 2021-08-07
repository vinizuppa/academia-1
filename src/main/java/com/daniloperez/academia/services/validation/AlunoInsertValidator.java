package com.daniloperez.academia.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.dto.AlunoNewDTO;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.resources.exceptions.FieldMessage;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunoNewDTO> {
	@Autowired
	private AlunoRepository repo;
	
	@Override
	public void initialize(AlunoInsert ann) {
	}

	@Override
	public boolean isValid(AlunoNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		Aluno aux = repo.findByEmail(objDto.getEmail());
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
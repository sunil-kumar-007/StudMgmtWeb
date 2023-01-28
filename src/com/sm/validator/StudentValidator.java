package com.sm.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sm.command.StudentCommand;

@Component
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> classType) {
		return classType.isAssignableFrom(StudentCommand.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		StudentCommand command = null;

		command = (StudentCommand) object;

		if (command.getId() <= 0) {
			errors.rejectValue("id", "id.invalid");
		}

		if (command.getName() == null || "".equals(command.getName())) {
			errors.rejectValue("name", "name.invalid");
		}
	}

}

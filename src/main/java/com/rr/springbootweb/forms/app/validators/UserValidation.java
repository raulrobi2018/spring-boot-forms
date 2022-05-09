package com.rr.springbootweb.forms.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rr.springbootweb.forms.app.models.domain.User;

//Lo registramos como componente para poder hacer inyección de dependencia
@Component
public class UserValidation implements Validator {

	// Identifica que clase va a validar
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	//El parámetro target será el objeto que recibirá, en este caso el objeto User
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		//Se utiliza el mensaje del archivo messages.properties definido para el campo name
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.user.name");
		
		//Utilizamos el pattern para validar
//		if(!user.getIdentifier().matches("[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")) {
//			errors.rejectValue("identifier", "pattern.user.identifier");
//		}
	}

}

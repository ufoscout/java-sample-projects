package ufo.gae.core.service.validation.impl;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import ufo.gae.core.service.validation.ValidationService;
import ufo.gae.core.service.validation.Validator;

@Component
public class JSR303ValidationService implements ValidationService {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@Override
	public <T> Validator<T> validator(T data) {
		return new JSR303Validator<T>(data, factory.getValidator());
	}

}

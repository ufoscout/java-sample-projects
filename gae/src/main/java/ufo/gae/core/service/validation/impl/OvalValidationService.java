package ufo.gae.core.service.validation.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;

import org.springframework.stereotype.Component;

import ufo.gae.core.service.validation.ValidationService;

//@Component
public class OvalValidationService implements ValidationService {

	Validator validator = new Validator(new AnnotationsConfigurer(), new BeanValidationAnnotationsConfigurer());

	@Override
	public <T> ufo.gae.core.service.validation.Validator<T> validator(T data) {
		return new OvalValidator<T>(data, validator);
	}

}

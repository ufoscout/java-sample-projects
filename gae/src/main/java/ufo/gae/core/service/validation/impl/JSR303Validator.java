package ufo.gae.core.service.validation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import ufo.gae.core.service.validation.ValidationException;
import ufo.gae.core.service.validation.ValidationResult;
import ufo.gae.core.service.validation.ValidationRule;
import ufo.gae.core.service.validation.Validator;
import ufo.gae.core.service.validation.ViolationCatalog;

public class JSR303Validator<T> implements Validator<T> {

	private final T data;
	private final javax.validation.Validator validator;
	private final List<ValidationRule<T>> validationRules = new ArrayList<ValidationRule<T>>();

	public JSR303Validator(T data, javax.validation.Validator validator) {
		this.data = data;
		this.validator = validator;
	}

	@Override
	public ValidationResult<T> validate() {
		final Map<String, List<String>> errors = new HashMap<String, List<String>>();
		Set<ConstraintViolation<T>> violations = validator.validate(data);

		Iterator<ConstraintViolation<T>> iter = violations.iterator();
		while (iter.hasNext()) {
			ConstraintViolation<T> violation = iter.next();
			String key = violation.getPropertyPath().toString();
			String error = violation.getMessage();
			addError(key, error, errors);
		}

		for (ValidationRule<T> validationRule : validationRules) {
			validationRule.validate(data, new ViolationCatalog() {
				@Override
				public void addViolation(String key, String message) {
					addError(key, message, errors);
				}
			});
		}

		return new ValidationResult<T>(data, errors );
	}

	@Override
	public Validator<T> addValidationRule(ValidationRule<T> valdiationRule) {
		validationRules.add(valdiationRule);
		return this;
	}

	private void addError(String key, String error, Map<String, List<String>> errors) {
		if (!errors.containsKey(key)) {
			errors.put(key, new ArrayList<String>());
		}
		errors.get(key).add(error);
	}

	@Override
	public void validateThrowException() throws ValidationException {
		ValidationResult<T> validationResult = validate();
		if (!validationResult.getViolations().isEmpty()) {
			throw new ValidationException(validationResult);
		}
	}
}

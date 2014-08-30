package ufo.gae.core.service.validation.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import ufo.gae.core.service.validation.ValidationException;
import ufo.gae.core.service.validation.ValidationResult;
import ufo.gae.core.service.validation.ValidationRule;
import ufo.gae.core.service.validation.Validator;
import ufo.gae.core.service.validation.ViolationCatalog;

public class OvalValidator<T> implements Validator<T> {

	private final T data;
	private final net.sf.oval.Validator validator;
	private final List<ValidationRule<T>> validationRules = new ArrayList<ValidationRule<T>>();

	public OvalValidator(T data, net.sf.oval.Validator validator) {
		this.data = data;
		this.validator = validator;
	}

	@Override
	public ValidationResult<T> validate() {
		final Map<String, List<String>> errors = new HashMap<String, List<String>>();
		addAllViolations( errors, validator.validate(data) );

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

	private void addAllViolations(Map<String, List<String>> errors, final List<ConstraintViolation> violations) {
		for (final ConstraintViolation constraintViolation : violations) {
			if (constraintViolation.getCauses() != null) {
				addAllViolations(errors, Arrays.asList(constraintViolation.getCauses()));
			}
			//			final Object validatedObject = constraintViolation.getValidatedObject();
			final OValContext context = constraintViolation.getContext();
			String key = constraintViolation.getErrorCode();
			if (context instanceof FieldContext) {
				key = ((FieldContext) context).getField().getName();
			}
			//			String error = constraintViolation.getErrorCode();
			String message = constraintViolation.getMessage();

			int removeMe;
			System.out.println("------------------------------------------");
			System.out.println(" - " + context.toString());
			System.out.println(" - " + constraintViolation.getCheckName());
			System.out.println(" - " + constraintViolation.getMessageTemplate());
			System.out.println(" - " + constraintViolation.getMessageVariables());
			System.out.println("------------------------------------------");
			addError(key, message, errors);

		}
	}

	@Override
	public Validator<T> addValidationRule(ValidationRule<T> valdiationRule) {
		validationRules.add(valdiationRule);
		return this;
	}

	@Override
	public void validateThrowException() throws ValidationException {
		ValidationResult<T> validationResult = validate();
		if (!validationResult.getViolations().isEmpty()) {
			throw new ValidationException(validationResult);
		}
	}

	private void addError(String key, String error, Map<String, List<String>> errors) {
		if (!errors.containsKey(key)) {
			errors.put(key, new ArrayList<String>());
		}
		errors.get(key).add(error);
	}
}

package ufo.gae.core.service.validation;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ValidationResult<?> validationResult;

	public ValidationException(ValidationResult<?> validationResult) {
		this.validationResult = validationResult;
	}

	public ValidationResult<?> getValidationResult() {
		return validationResult;
	}

}

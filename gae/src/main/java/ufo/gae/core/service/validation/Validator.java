package ufo.gae.core.service.validation;

public interface Validator<T> {

	/**
	 * Validate the bean and return the {@link ValidationResult} as result of the validation
	 * @return
	 */
	ValidationResult<T> validate();

	/**
	 * Validate the bean. If there are validation errors a {@link ValidationException}
	 * containing the {@link ValidationResult} is thrown
	 * 
	 * @throws ValidationException if there are validation errors
	 */
	void validateThrowException() throws ValidationException;

	/**
	 * Add a new custom validation rule to be used during the validation process.
	 * 
	 * @param validationRule
	 * @return
	 */
	Validator<T> addValidationRule(ValidationRule<T> validationRule);

}

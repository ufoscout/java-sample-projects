package ufo.gae.core.service.validation;


public interface ValidationService {

	/**
	 * Validate an object and, if needed, return a map of validation errors
	 * 
	 * @param object
	 * @return
	 */
	<T> Validator<T> validator(T data);

}

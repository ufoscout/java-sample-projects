package ufo.gae.core.service.validation;

public interface ValidationRule<T> {

	void validate(T data, ViolationCatalog violationCatalog);

}

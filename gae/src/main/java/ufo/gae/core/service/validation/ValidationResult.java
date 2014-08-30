package ufo.gae.core.service.validation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Map<String, List<String>> violations;
	private final T data;

	public ValidationResult(T data) {
		this(data, new HashMap<String, List<String>>());
	}

	public ValidationResult(T data, Map<String, List<String>> violations) {
		this.data = data;
		this.violations = violations;

	}

	public T getData() {
		return data;
	}

	public Map<String, List<String>> getViolations() {
		return violations;
	}

}

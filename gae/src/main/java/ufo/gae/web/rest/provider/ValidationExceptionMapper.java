package ufo.gae.web.rest.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ufo.gae.core.service.validation.ValidationException;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException ex) {
		return Response.status(Status.BAD_REQUEST).
				entity(ex.getValidationResult().getViolations()).
				type(MediaType.APPLICATION_JSON).
				build();
	}
}

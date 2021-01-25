package pw.komarov.taxi.rest.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pw.komarov.taxi.persistence.exceptions.EntityObjectHeldException;
import pw.komarov.taxi.persistence.exceptions.wrappers.EntityObjectHeldExceptionWrapper;

@Provider
public class EntityObjectHeldExceptionMapper implements ExceptionMapper<EntityObjectHeldException> {
	@Override
	public Response toResponse(EntityObjectHeldException e) {
		return Response.status(Status.BAD_REQUEST).entity(new EntityObjectHeldExceptionWrapper(e)).build();
	}
}
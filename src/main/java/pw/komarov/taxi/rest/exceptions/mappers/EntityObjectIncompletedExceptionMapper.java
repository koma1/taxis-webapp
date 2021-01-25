package pw.komarov.taxi.rest.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pw.komarov.taxi.persistence.exceptions.EntityObjectIncompletedException;
import pw.komarov.taxi.persistence.exceptions.wrappers.EntityObjectIncompletedExceptionWrapper;

@Provider
public class EntityObjectIncompletedExceptionMapper implements ExceptionMapper<EntityObjectIncompletedException> {
	@Override
	public Response toResponse(EntityObjectIncompletedException e) {
		return Response.status(Status.BAD_REQUEST).entity(new EntityObjectIncompletedExceptionWrapper(e)).build();
	}
}
package pw.komarov.taxi.rest.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;
import pw.komarov.taxi.persistence.exceptions.wrappers.EntityObjectNotFoundExceptionWrapper;

@Provider
public class EntityObjectNotFoundExceptionMapper implements ExceptionMapper<EntityObjectNotFoundException> {
	@Override
	public Response toResponse(EntityObjectNotFoundException e) {
		return Response.status(Status.NOT_FOUND).entity(new EntityObjectNotFoundExceptionWrapper(e)).build();
	}
}
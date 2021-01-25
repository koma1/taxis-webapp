package pw.komarov.taxi.rest.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pw.komarov.taxi.persistence.exceptions.EntityObjectPersistException;
import pw.komarov.taxi.persistence.exceptions.wrappers.EntityObjectPersistExceptionWrapper;

@Provider
public class EntityObjectPersistExceptionMapper implements ExceptionMapper<EntityObjectPersistException> {
	@Override
	public Response toResponse(EntityObjectPersistException e) {
		return Response
				.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new EntityObjectPersistExceptionWrapper(e)).build();
	}
}
package pw.komarov.taxi.rest.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pw.komarov.taxi.rest.exceptions.UnsupportedMediaException;
import pw.komarov.taxi.rest.exceptions.UnsupportedMediaExceptionWrapper;

@Provider
public class UnsupportedMediaExceptionMapper implements ExceptionMapper<UnsupportedMediaException> {
	@Override
	public Response toResponse(UnsupportedMediaException e) {
		return Response.status(Status.BAD_REQUEST).entity(new UnsupportedMediaExceptionWrapper(e)).build();
	}	
}
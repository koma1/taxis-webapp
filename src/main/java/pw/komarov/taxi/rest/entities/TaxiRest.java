package pw.komarov.taxi.rest.entities;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import pw.komarov.taxi.persistence.entity.TaxiEntity;
import pw.komarov.taxi.persistence.exceptions.*;
import pw.komarov.taxi.persistence.services.TaxiService;
import pw.komarov.taxi.rest.exceptions.UnsupportedMediaException;

/*
 * All request&response data must be in JSON or XML
 * 
	GET /taxi 	 					all taxi services
	GET /taxi/{id} 					taxi service by Id
	GET /taxi?{name}{phone}{city}   taxi services by mixing applied filters
	GET /taxi?{text}   				taxi services by any match text filtering
	DELETE /taxi/{id}				delete taxi by Id
	POST   /taxi					modify taxi info
	PUT    /taxi					create taxi info
*/

@Path("/taxi")
public class TaxiRest {
	private static TaxiService taxiService = new TaxiService();
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	public Response getFiltered(
			@QueryParam("name") String name,
			@QueryParam("phone") String phone,
			@QueryParam("city") String city,
			@QueryParam("text") String text) {
		List<TaxiEntity> taxis = taxiService.find(name, phone, city, text);
		return Response.ok(new GenericEntity<List<TaxiEntity>>(taxis){}).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getHtml(
			@QueryParam("name") String name,
			@QueryParam("phone") String phone,
			@QueryParam("city") String city,
			@QueryParam("text") String text) {
		List<TaxiEntity> taxis = taxiService.find(name, phone, city, text);
		
	    Map<String, Object> model = new HashMap<>();        
	    model.put("taxis", taxis);
	    
	    return new Viewable("/taxis", model);
	}
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Integer id) throws EntityObjectNotFoundException {
		TaxiEntity taxi = taxiService.getEntity(id);
		if(taxi == null)
			throw new EntityObjectNotFoundException(TaxiEntity.class, id);
		else
			return Response.ok(taxi).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getHtmlById(@PathParam("id") Integer id) throws EntityObjectNotFoundException {
		TaxiEntity taxi = taxiService.getEntity(id);
		if(taxi == null)
			return Response
					.status(Status.NOT_FOUND)
					.entity(new Viewable("/enf", new EntityObjectNotFoundException(TaxiEntity.class, id)))
				.build();
		else {
		    Map<String, Object> model = new HashMap<>();        
		    model.put("taxi", taxi);
		    
		    return Response.ok(new Viewable("/taxi", taxi)).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") Integer id) throws EntityObjectException {
		taxiService.delete(id);
		
		return Response.noContent().build();
	}
	
	@POST
	public Response updateById(TaxiEntity taxi) throws EntityObjectException, UnsupportedMediaException {
		if(taxi == null)
			throw new UnsupportedMediaException("taxi is null!");

		taxiService.save(taxi);
		
		return Response.ok(taxi).build();
	}

	@PUT 
	public Response create(TaxiEntity taxi) throws EntityObjectException, UnsupportedMediaException {		
		if(taxi == null)
			throw new UnsupportedMediaException("taxi is null!");
		if(taxi.getId() != null)
			throw new UnsupportedMediaException("taxi id must be the null!");
		
		taxiService.save(taxi);
		
		return Response.created(URI.create(String.format("%s/%d", uriInfo.getAbsolutePath(), taxi.getId()))).entity(taxi).build();
	}
}
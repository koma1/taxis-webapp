package pw.komarov.taxi.rest.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.net.URI;

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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import pw.komarov.taxi.persistence.entity.CityEntity;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;
import pw.komarov.taxi.persistence.services.CityService;
import pw.komarov.taxi.rest.exceptions.UnsupportedMediaException;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/cities")
public class CityRest {
	private static CityService cityService = new CityService();
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getHtml(
			@QueryParam("name") String name,
			@QueryParam("country_name") String countryName,
			@QueryParam("country_id") Integer countryId) {
		
		List<CityEntity> cities = cityService.find(name, countryName, countryId);
		
	    Map<String, Object> model = new HashMap<>();        
	    model.put("cities", cities);
	    
	    return new Viewable("/cities", model);
	}

	@GET
	public Response get(
			@QueryParam("name") String name,
			@QueryParam("country_name") String countryName,
			@QueryParam("country_id") Integer countryId) {
		
		List<CityEntity> cities = cityService.find(name, countryName, countryId);
		return Response.ok(new GenericEntity<List<CityEntity>>(cities){}).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getHtmlById(@PathParam("id") Integer id) {
		CityEntity city = cityService.getEntity(id);
		if(city == null)
			return Response
					.status(Status.NOT_FOUND)
					.entity(new Viewable("/enf", new EntityObjectNotFoundException(CityEntity.class, id)))
				.build();
		else {
		    Map<String, Object> model = new HashMap<>();        
		    model.put("city", city);
		    
		    return Response.ok(new Viewable("/city", city)).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Integer id) throws EntityObjectNotFoundException {
		CityEntity city = cityService.getEntity(id);
		if(city == null)
			throw new EntityObjectNotFoundException(CityEntity.class, id);
		else
			return Response.ok(city).build();
	}

	@PUT 
	public Response create(CityEntity city) throws EntityObjectException, UnsupportedMediaException {
		if(city == null)
			throw new UnsupportedMediaException("city is null!");
		if(city.getId() != null)
			throw new UnsupportedMediaException("city id must be the null!");

		cityService.save(city);

		return Response.created(URI.create(String.format("%s/%d", uriInfo.getAbsolutePath(), city.getId()))).build();
	}
	
	@POST
	public Response updateById(CityEntity city) throws EntityObjectException, UnsupportedMediaException {
		if(city == null)
			throw new UnsupportedMediaException("city is null!");

		cityService.save(city);
		
		return Response.ok(city).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") Integer id) throws EntityObjectException {
		cityService.delete(id);
		
		return Response.noContent().build();
	}
}
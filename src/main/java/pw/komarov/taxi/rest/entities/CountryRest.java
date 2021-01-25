package pw.komarov.taxi.rest.entities;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.glassfish.jersey.server.mvc.Viewable;

import pw.komarov.taxi.persistence.entity.CountryEntity;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;
import pw.komarov.taxi.persistence.services.CountryService;
import pw.komarov.taxi.rest.exceptions.UnsupportedMediaException;

@Path("/countries")
public class CountryRest {
	private static CountryService countryService = new CountryService();
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getHtml(@QueryParam("name") String name) {
		List<CountryEntity> countries = countryService.find(name);
		
	    Map<String, Object> model = new HashMap<>();        
	    model.put("countries", countries);
	    
	    return new Viewable("/countries", model);
	}
	
	@GET
	public Response get(@QueryParam("name") String name) {
		List<CountryEntity> countries = countryService.find(name);
		return Response.ok(new GenericEntity<List<CountryEntity>>(countries){}).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getHtmlById(@PathParam("id") Integer id) {
		CountryEntity country = countryService.getEntity(id);
		if(country == null)
			return Response
					.status(Status.NOT_FOUND)
					.entity(new Viewable("/enf", new EntityObjectNotFoundException(CountryEntity.class, id)))
				.build();
		else {
		    Map<String, Object> model = new HashMap<>();        
		    model.put("country", country);
		    
		    return Response.ok(new Viewable("/country", country)).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Integer id) throws EntityObjectNotFoundException {
		CountryEntity country = countryService.getEntity(id);
		if(country == null)
			throw new EntityObjectNotFoundException(CountryEntity.class, id);
		else
			return Response.ok(country).build();
	}
	
	@PUT 
	public Response create(CountryEntity country) throws EntityObjectException, UnsupportedMediaException {
		if(country == null)
			throw new UnsupportedMediaException("country is null!");
		if(country.getId() != null)
			throw new UnsupportedMediaException("country id must be the null!");
		
		countryService.save(country);

		return Response.created(URI.create(String.format("%s/%d", uriInfo.getAbsolutePath(), country.getId()))).build();
	}
	
	@POST
	public Response updateById(CountryEntity country) throws EntityObjectException, UnsupportedMediaException {
		if(country == null)
			throw new UnsupportedMediaException("country is null!");

		countryService.save(country);
		
		return Response.ok(country).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") Integer id) throws EntityObjectException {
		countryService.delete(id);
		
		return Response.noContent().build();
	}
}
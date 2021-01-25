package pw.komarov.taxi.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import pw.komarov.taxi.rest.exceptions.mappers.EntityObjectHeldExceptionMapper;
import pw.komarov.taxi.rest.exceptions.mappers.EntityObjectIncompletedExceptionMapper;
import pw.komarov.taxi.rest.exceptions.mappers.EntityObjectNotFoundExceptionMapper;
import pw.komarov.taxi.rest.exceptions.mappers.EntityObjectPersistExceptionMapper;
import pw.komarov.taxi.rest.exceptions.mappers.UnsupportedMediaExceptionMapper;

@ApplicationPath("/rest")
public class TaxiApplication extends ResourceConfig {
    public TaxiApplication() {
        //JSON
    	registerClasses(org.glassfish.jersey.moxy.json.MoxyJsonFeature.class);
        registerClasses(JsonMoxyConfigurationContextResolver.class);
        //JSP Views
        property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/jsp/rest");
        register(JspMvcFeature.class);
        
        register(MessageBodyWriterTextPlain.class);

        register(EntityObjectNotFoundExceptionMapper.class);
        register(EntityObjectHeldExceptionMapper.class);
        register(EntityObjectIncompletedExceptionMapper.class);
        register(EntityObjectPersistExceptionMapper.class);
        register(UnsupportedMediaExceptionMapper.class);
        
        packages("pw.komarov.taxi.rest.entities");
    }
}
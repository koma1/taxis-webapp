package pw.komarov.taxi.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.TEXT_PLAIN)
public class MessageBodyWriterTextPlain implements MessageBodyWriter<Object> {

	@Override
	public long getSize(Object object, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
		return 0; //deprecated
	}

	@Override
	public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(Object object, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> values, OutputStream outputStream)
					throws IOException, WebApplicationException {
		
		try(Writer writer = new PrintWriter(outputStream)) {
			writer.write(object.toString());
			writer.flush();
		}
	}

}

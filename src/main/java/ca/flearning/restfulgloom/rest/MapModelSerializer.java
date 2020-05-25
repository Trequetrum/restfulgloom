package ca.flearning.restfulgloom.rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ca.flearning.restfulgloom.rest.dto.MapModelDto;

public class MapModelSerializer extends StdSerializer<MapModelDto> {

	private static final long serialVersionUID = 7378778890983876266L;

	public MapModelSerializer() {
        this(null);
    }
   
    public MapModelSerializer(Class<MapModelDto> t) {
        super(t);
    }
 
	@Override
	public void serialize(MapModelDto value, JsonGenerator jgen, SerializerProvider provider) 
			throws IOException, JsonProcessingException {
  
		jgen.writeObject(value.getResponse());
    }
}
package ca.flearning.restfulgloom.rest.hateoas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

/******
 * General Purpose DTO to return HATEOAS-linked responses to the client.
 ******/
public class MapModelDto extends RepresentationModel<MapModelDto>{
	
	private Map<String, Object> response = new HashMap<>();
	
	public MapModelDto() {}
	public MapModelDto(Map<String, Object> map) {
		this.response = map;
	}
	
	public void putAll(Map<String, Object> map) {
		this.response.putAll(map);
	}
	public void put(String key, Object value) {
		this.response.put(key, value);
	}
	public Map<String, Object> getResponse() {
		return response;
	}
	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}
	
	
}

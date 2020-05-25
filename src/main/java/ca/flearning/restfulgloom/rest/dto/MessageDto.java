package ca.flearning.restfulgloom.rest.dto;

public class MessageDto extends MapModelDto{
	public MessageDto(String message) {
		put("Message", message);
	}
}

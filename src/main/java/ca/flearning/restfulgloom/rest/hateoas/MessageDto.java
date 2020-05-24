package ca.flearning.restfulgloom.rest.hateoas;

public class MessageDto extends MapModelDto{
	public MessageDto(String message) {
		put("Message", message);
	}
}

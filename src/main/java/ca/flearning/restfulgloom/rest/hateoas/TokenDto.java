package ca.flearning.restfulgloom.rest.hateoas;

import org.springframework.hateoas.RepresentationModel;

/******
 * General Purpose DTO to return HATEOAS-linked responses to the client.
 ******/
public class TokenDto extends RepresentationModel<TokenDto>{
	
	private String token = "";
	
	public TokenDto() {}
	public TokenDto(String token) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

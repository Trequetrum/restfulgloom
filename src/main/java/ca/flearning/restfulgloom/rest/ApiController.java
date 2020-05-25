package ca.flearning.restfulgloom.rest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.flearning.restfulgloom.rest.dto.MessageDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Value("${ca.flearning.restfulgloom.api.message.welcome}")
	private String welcomeMessage;
	
	@Value("${ca.flearning.restfulgloom.api.message.auth}")
	private String authenticateMessage;

	@GetMapping()
	public MessageDto rootApiEndpoint() throws Exception{
		MessageDto topLinks = new MessageDto(welcomeMessage);
		
		topLinks.add(linkTo(ApiController.class).withSelfRel());
		topLinks.add(linkTo(methodOn(ApiController.class).auth()).withRel("authenticate"));
		topLinks.add(linkTo(ApiController.class).slash("dm").withRel("datamodel"));
		topLinks.add(Link.of("https://github.com/Trequetrum/RestfulGloom", "github"));
		return topLinks;
	}
	
	@GetMapping("/auth")
	public MessageDto auth() throws Exception{
		MessageDto authenticateLinks = new MessageDto(authenticateMessage);
		
		authenticateLinks.add(linkTo(methodOn(ApiController.class).auth()).withSelfRel());
		authenticateLinks.add(linkTo(methodOn(ApiController.class).rootApiEndpoint()).withRel("api"));
		authenticateLinks.add(linkTo(methodOn(ApiController.class).devLogin()).withRel("devLogin"));
		authenticateLinks.add(linkTo(methodOn(LoginController.class).refresh("JwtToken")).withRel("refreshJwt"));
		
		return authenticateLinks;
	}
	
	@GetMapping("/auth/devlogin")
	public MessageDto devLogin() throws Exception{
		MessageDto getDevLogin = new MessageDto("Uhm. Why iz you GET here? POST plz");
		getDevLogin.add(linkTo(methodOn(ApiController.class).devLogin()).withSelfRel());
		getDevLogin.add(linkTo(methodOn(ApiController.class).rootApiEndpoint()).withRel("api"));
		return getDevLogin;
	}
	
}

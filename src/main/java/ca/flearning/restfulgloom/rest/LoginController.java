package ca.flearning.restfulgloom.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.flearning.restfulgloom.dao.RefreshTokenRepository;
import ca.flearning.restfulgloom.entities.RefreshToken;
import ca.flearning.restfulgloom.rest.dto.TokenDto;
import ca.flearning.restfulgloom.rest.errors.ForbiddenException;
import ca.flearning.restfulgloom.security.RefreshTokenGenerator;
import ca.flearning.restfulgloom.security.JWTToken;
import ca.flearning.restfulgloom.security.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
    private RefreshTokenRepository refreshTokenRepository;
	@Autowired
	private RefreshTokenGenerator refreshTokenGenerator;
	
    @PostMapping("/devlogin")
    public TokenDto devLogin(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception
    {
        User user = new User();
        user.setName(name);
        JWTToken jwt = new JWTToken(user);

        // create a refresh token and persist it to the db
        RefreshToken refreshToken = refreshTokenGenerator.generateToken(user);
        
        // return token with a link to refresh the token
        TokenDto rtn = new TokenDto(jwt.getCredentials().getToken());
        rtn.add(linkTo(methodOn(LoginController.class).refresh(refreshToken.getToken())).withRel("refresh"));
        
        return rtn;
    }


    @GetMapping("/refresh/{refreshToken}")
    public TokenDto refresh(@PathVariable("refreshToken") String refreshTokenStr) throws Exception {
        JWTToken token;

        List<RefreshToken> refreshTokenList = refreshTokenRepository.findByToken(refreshTokenStr);

        if (refreshTokenList.size() == 0) {
            throw new ForbiddenException("Refresh token not valid");
        } else if (refreshTokenList.size() == 1) {
            // process it and return a new JWT
            RefreshToken refreshToken = refreshTokenList.get(0);

            // is it expired?
            if (refreshToken.getExpiry().before(new Date(System.currentTimeMillis())) ) {
                throw new ForbiddenException("Refresh token expired");
            }

            // otherwise, it's good!
            User user = new User();
            user.setName(refreshToken.getUsername());
            token = new JWTToken(user);

            // create a refresh token and persist it to the db
            RefreshToken newRefreshToken = refreshTokenGenerator.generateToken(user);
            
           
            // return token with a link to refresh the token
            TokenDto rtn = new TokenDto(token.getCredentials().getToken());
            rtn.add(linkTo(methodOn(LoginController.class).refresh(newRefreshToken.getToken())).withRel("refresh"));
            return rtn;
        } else {
            // uhh, panic? The DB should enforce that as a unique field.
            throw new RuntimeException("DB should never have multiple entries for the same refresh token");
        }
        // no return because it's unreachable.
    }

    
    
}
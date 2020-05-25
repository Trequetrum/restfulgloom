package ca.flearning.restfulgloom.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import ca.flearning.restfulgloom.dao.RefreshTokenRepository;
import ca.flearning.restfulgloom.entities.RefreshToken;

@Component
public final class RefreshTokenGenerator {
	private static final int REFRESH_TOKEN_LEN = 16;
    private static final long EXPIRATION_TIME = 86_400_000;  // 24 hours

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    
    public RefreshToken generateToken() {
        RefreshToken refreshToken = new RefreshToken();
        try {
            // generate a 32 byte random
            byte[] refreshTokenBytes = new byte[REFRESH_TOKEN_LEN];
            SecureRandom.getInstanceStrong().nextBytes(refreshTokenBytes);
            refreshToken.setToken(new String(Hex.encode(refreshTokenBytes)));
            refreshToken.setExpiry(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        } catch (NoSuchAlgorithmException e) {
            // something went wrong, don't try to return anything
            e.printStackTrace();
        }
        return refreshToken;
    }
    
    public RefreshToken generateToken(User user) throws Exception {
    	return generateToken(user, 1);
    }
    
    private RefreshToken generateToken(User user, int attempt) throws Exception {
    	
    	RefreshToken refreshToken = generateToken();
        refreshToken.setUsername(user.getName());
        
        try {
            refreshTokenRepository.save(refreshToken);
        } catch (Exception e) {
        	if(attempt > 5) {
        		throw e;
        	}else {
        		generateToken(user, attempt+1);
        	}
        }
        
        return refreshToken;
    }
}

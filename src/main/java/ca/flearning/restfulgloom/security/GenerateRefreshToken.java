package ca.flearning.restfulgloom.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;

import ca.flearning.restfulgloom.dao.RefreshTokenRepository;
import ca.flearning.restfulgloom.entities.RefreshToken;

public final class GenerateRefreshToken {
	private static final int REFRESH_TOKEN_LEN = 16;
    private static final long EXPIRATION_TIME = 86_400_000;  // 24 hours

    @Autowired
    private static RefreshTokenRepository refreshTokenRepository;
    
    public static RefreshToken generateToken() {
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
    
    public static RefreshToken generateToken(User user) {
        // create a refresh token and persist it to the db
        RefreshToken refreshToken = generateToken();
        refreshToken.setUsername(user.getName());
        try {
            refreshTokenRepository.save(refreshToken);
        } catch (Exception e) {
            // probably the random token happened to be a duplicate, and the DB threw
            // a constraint violation.
            // Try again.

            refreshToken = GenerateRefreshToken.generateToken();
            refreshToken.setUsername(user.getName());
            refreshTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }
}

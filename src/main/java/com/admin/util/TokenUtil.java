package com.admin.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {
	
private static final String TOKEN_SECRETE = "karur";
	
	public String createToken(Long id) {
		
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRETE);
		
		String token= JWT.create().withClaim("user_id", id).sign(algorithm);
		
		
		return token;
	}

	
	public Long decodeToken(String token) {
		
		Long userId;
		
		Verification verification = null;
		
		
		verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRETE));
		
		
		
	
		JWTVerifier jwtVerifier = verification.build();
		
		DecodedJWT decodeJWt = jwtVerifier.verify(token);
		
		Claim claim = decodeJWt.getClaim("user_id");
		
		userId = claim.asLong();
		
		return userId;
		
		
		
	}

}

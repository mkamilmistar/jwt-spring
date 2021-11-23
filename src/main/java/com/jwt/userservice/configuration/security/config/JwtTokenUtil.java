package com.jwt.userservice.configuration.security.config;

import com.jwt.userservice.configuration.security.token.TokenContent;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.KeyPair;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;

	@Autowired
	private KeyPair keyPair;

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    //for retrieveing any information from token we will need the secret key
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails, TokenContent tokenContent, Integer expirationInMs, String channel, String scopeType) {
		Map<String, Object> claims = new HashMap<>();

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

		String authoritiesString = "";
		for (GrantedAuthority grantedAuthority : authorities) {
			authoritiesString = authoritiesString.concat(grantedAuthority.getAuthority());
	    }

		if (!authoritiesString.equals("")) {
//			customer
			claims.put("isRegistered", tokenContent.getIsRegistered());
			claims.put("customerType", tokenContent.getCustomerType());
			claims.put("customerId", tokenContent.getCustomerId());
			claims.put("authorities", List.of(channel));
			claims.put("iss", channel);
			claims.put("scope", scopeType);
			claims.put("client_id", tokenContent.getCustomerId());

//			staff
			claims.put("userId", tokenContent.getUserId());
			claims.put("fullname", tokenContent.getFullName());
			claims.put("user_id", tokenContent.getUserId());
			claims.put("branchNumber", tokenContent.getBranchNumber());
			claims.put("role", tokenContent.getRole());
			claims.put("controlUnit", tokenContent.getControlUnit());
			claims.put("tellerId", tokenContent.getTellerId());
			claims.put("tellerUserId", tokenContent.getTellerUserId());
			claims.put("parentControlUnit", tokenContent.getParentControlUnit());
		}

		return doGenerateToken(claims, userDetails.getUsername(), expirationInMs);
	}

	public String doGenerateToken(Map<String, Object> claims, String subject, Integer expirationInMs) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirationInMs))
				.signWith(SignatureAlgorithm.RS256, keyPair.getPrivate()).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean validateTokenOnly(String authToken) {
		try {
			Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token).getBody();
		return claims.getSubject();

	}

	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token).getBody();
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		ArrayList<String> authorities = claims.get("authorities", ArrayList.class);
		authorities.forEach(c-> authorityList.add(new SimpleGrantedAuthority(c)));
		return authorityList;

	}
}

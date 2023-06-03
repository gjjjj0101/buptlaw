package edu.bupt.law.security.utils;


import edu.bupt.law.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTUtils {

    private static final String AUTHORITIES_KEY = "roles";

    private static String secret = Base64.getEncoder().encodeToString(SecurityConstants.SECRET_KEY.getBytes());


    public static String createTokenByAuthentication(Authentication authentication) {
        String username = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Claims claims = Jwts.claims().setSubject(username);

        if (!authorities.isEmpty()) {
            claims.put(AUTHORITIES_KEY,
                    authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        }

        Date now = new Date();
        int expireDelay = Integer.parseInt(SecurityConstants.EXPIRATION_TIME);
        Date validity = new Date(now.getTime() + expireDelay * 10000L);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public static Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        Object authoritiesClaim = claims.get(AUTHORITIES_KEY);

        Collection<? extends GrantedAuthority> authorities =
                authoritiesClaim == null
                        ? AuthorityUtils.NO_AUTHORITIES
                        : AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString());
        try {
            String principal = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(principal, token, authorities);

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

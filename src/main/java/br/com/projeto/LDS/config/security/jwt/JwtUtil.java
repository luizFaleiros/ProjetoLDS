package br.com.projeto.LDS.config.security.jwt;


import br.com.projeto.LDS.enums.PerfilEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setClaims(addClaims(authorities))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean ValidtToken(String token) {
        Boolean isValidToken = false;
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (Objects.nonNull(username) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
                isValidToken = true;
            }
        }
        return isValidToken;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }


    public String getUsername(String token) {
        String username = null;
        Claims claims = getClaims(token);

        if (Objects.nonNull(claims)) {
            username = claims.getSubject();
        }
        return username;
    }

    private Map<String, Object> addClaims(Collection<? extends GrantedAuthority> authorities){
            Map<String, Object> auths = new HashMap<>();
            auths.put("Roles", authorities.stream().map(authorit -> authorit.getAuthority()).collect(Collectors.toList()));
        return  auths;
    }
}

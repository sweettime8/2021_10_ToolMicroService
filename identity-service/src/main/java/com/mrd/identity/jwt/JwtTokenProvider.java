package com.mrd.identity.jwt;

import com.mrd.identity.auth.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @author ducnh
 */
@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    private final long ACCESS_JWT_EXPIRATION = 604800000L;//7 day

    public String generateToken(CustomUserDetails userDetails) {
        // Lấy thông tin user
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(userDetails.getUser().getUuid())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUuidFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }


}

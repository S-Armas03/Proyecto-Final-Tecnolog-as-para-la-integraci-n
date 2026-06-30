/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.utils;

/**
 *
 * @author sergg
 */
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class JwtUtil {

    private static final String SECRET_KEY = "8fK#2Lm@7QpXvR9sT1uW4yZ6aBcD3eFgH8jKlMnP0qRsTu";

    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private JwtUtil() {
    }

    public static Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static Integer obtenerIdUsuario(Claims claims) {
        return claims.get("idUsuario", Integer.class);
    }

    public static String obtenerRol(Claims claims) {
        return claims.get("rol", String.class);
    }
}

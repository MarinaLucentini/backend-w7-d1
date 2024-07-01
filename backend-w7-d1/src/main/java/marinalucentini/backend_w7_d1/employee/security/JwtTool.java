package marinalucentini.backend_w7_d1.employee.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import marinalucentini.backend_w7_d1.employee.Employee;
import marinalucentini.backend_w7_d1.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTool {
    @Value("${jwt.secret}")
    private String secret;
    public String createToken(Employee employee){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 ))
                .subject(String.valueOf(employee.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }



}

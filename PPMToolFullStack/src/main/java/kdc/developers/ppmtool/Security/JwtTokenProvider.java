package kdc.developers.ppmtool.Security;

import io.jsonwebtoken.*;
import kdc.developers.ppmtool.Entities.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static kdc.developers.ppmtool.Security.SecurityConstants.EXPIRATION_TIME;
import static kdc.developers.ppmtool.Security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {

    //Generate the token

    public String generateToken(Authentication authentication){
        Usuario user= (Usuario) authentication.getPrincipal();
        Date now= new Date(System.currentTimeMillis());

        Date expiryDate=new Date(now.getTime()+EXPIRATION_TIME);

        String userId=Long.toString((user.getId()));

        Map<String,Object> claims =new HashMap<>();
        claims.put("id",userId);
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullname());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }
    //Validate the token

    public boolean ValidateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }
        catch(MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }
        catch(ExpiredJwtException ex){
            System.out.println("Expired JWT Token");
        }
        catch (UnsupportedJwtException ex){
            System.out.println("Unssoported JWT Token");
        }
        catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    //get user Id from Token

    public Long getUserIdFromJWT(String token){
        Claims claims=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        //Long id2 = Long.parseLong(claims.getId());
       String id = (String) claims.get("id");
          return Long.parseLong(id);
    }
}

package kdc.developers.ppmtool.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kdc.developers.ppmtool.Entities.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

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
        claims.put("fullname",user.getFullname());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }
    //Validate the token

    //get user Id from Token
}

package kdc.developers.ppmtool.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTLoginSuccessResponse {

    boolean success;
    String token;

    @Override
    public String toString() {
        return "JWTLoginSuccessResponse{"+
                "success="+success+
                ",token='"+token+'\''+
                '}';
    }
}

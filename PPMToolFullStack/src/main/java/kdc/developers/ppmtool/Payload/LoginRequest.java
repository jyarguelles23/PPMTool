package kdc.developers.ppmtool.Payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Username cannot be blank")
    String username;
    @NotBlank(message = "Username cannot be blank")
    String password;
}

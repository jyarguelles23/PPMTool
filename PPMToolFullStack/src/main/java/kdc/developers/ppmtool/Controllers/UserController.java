package kdc.developers.ppmtool.Controllers;

import kdc.developers.ppmtool.Entities.Usuario;
import kdc.developers.ppmtool.Payload.JWTLoginSuccessResponse;
import kdc.developers.ppmtool.Payload.LoginRequest;
import kdc.developers.ppmtool.Security.JwtTokenProvider;
import kdc.developers.ppmtool.Services.MapValidationServiceErrors;
import kdc.developers.ppmtool.Services.UserService;
import kdc.developers.ppmtool.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static kdc.developers.ppmtool.Security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;
    @Autowired
    MapValidationServiceErrors mapValidationServiceErrors;

    @Autowired
    UserValidator validator;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult result){
        ResponseEntity<?> errorMap=mapValidationServiceErrors.MapValidationService(result);
        if (errorMap!=null) return errorMap;
           Authentication authentication=authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUsername(),
                           loginRequest.getPassword()
                   )
           );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=TOKEN_PREFIX+ jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Usuario usuario, BindingResult result){
        //Validate passwords match
        //password and confirm equals
        validator.validate(usuario,result);

        ResponseEntity<?> errorMap=mapValidationServiceErrors.MapValidationService(result);

        if (errorMap!=null) return errorMap;

        return new ResponseEntity<Usuario>(service.saveUser(usuario), HttpStatus.CREATED);
    }

}

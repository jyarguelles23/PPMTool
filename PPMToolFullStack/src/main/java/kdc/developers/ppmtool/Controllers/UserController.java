package kdc.developers.ppmtool.Controllers;

import kdc.developers.ppmtool.Entities.Usuario;
import kdc.developers.ppmtool.Services.MapValidationServiceErrors;
import kdc.developers.ppmtool.Services.UserService;
import kdc.developers.ppmtool.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;
    @Autowired
    MapValidationServiceErrors mapValidationServiceErrors;

    @Autowired
    UserValidator validator;

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

package kdc.developers.ppmtool.Validator;

import kdc.developers.ppmtool.Entities.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
      Usuario user=(Usuario)o;

      if(user.getPassword().length() < 6){
          errors.rejectValue("password","Length","Password must be at least 6 characters");
      }

      if(!user.getPassword().equals(user.getConfirmPassword())){
          errors.rejectValue("password","Match","Passwords must match");
      }
    }
}

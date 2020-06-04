package kdc.developers.ppmtool.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;

@Service
public class MapValidationServiceErrors {

   public ResponseEntity<?> MapValidationService(BindingResult result){
       if(result.hasErrors()){
           List<FieldError> field=result.getFieldErrors();
           HashMap<String,String> error_map=new HashMap<>();
           for (FieldError error : result.getFieldErrors() ){
               error_map.put(error.getField(),error.getDefaultMessage());
           }
           return new ResponseEntity<HashMap<String,String>>(error_map, HttpStatus.BAD_REQUEST);
       }
       return null;
   }
}

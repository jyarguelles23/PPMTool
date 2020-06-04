package kdc.developers.ppmtool.Controllers;

import kdc.developers.ppmtool.Entities.Project;
import kdc.developers.ppmtool.Services.MapValidationServiceErrors;
import kdc.developers.ppmtool.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService service;
    @Autowired
    MapValidationServiceErrors errorservice;
    @PostMapping("")
    public ResponseEntity<?>  createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errormap=errorservice.MapValidationService(result);
        if(errormap!=null) return errormap;
       return  new ResponseEntity<Project>(service.saveOrUpdate(project), HttpStatus.CREATED);
    }

}

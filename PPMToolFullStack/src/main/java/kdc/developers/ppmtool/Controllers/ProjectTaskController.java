package kdc.developers.ppmtool.Controllers;

import kdc.developers.ppmtool.Entities.Project;
import kdc.developers.ppmtool.Entities.ProjectTask;
import kdc.developers.ppmtool.Services.MapValidationServiceErrors;
import kdc.developers.ppmtool.Services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class ProjectTaskController {

    @Autowired
    ProjectTaskService service;

    @Autowired
    MapValidationServiceErrors errorservice;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask project, BindingResult result,@PathVariable String backlog_id){
      //make this in one line of code
        ResponseEntity<?> errormap=errorservice.MapValidationService(result);
        if(errormap!=null) return errormap;

        return  new ResponseEntity<ProjectTask>(service.addProjectTask(backlog_id,project), HttpStatus.CREATED);
    }
}

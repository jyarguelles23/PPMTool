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
import java.util.List;

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

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBackLog(@PathVariable String backlog_id){
        return service.findBacklogId(backlog_id);
    }

    @GetMapping("/{backlog_id}/{projectSequence}")
    public ResponseEntity<?>  getProjectTask(@PathVariable  String backlog_id,@PathVariable String projectSequence){

        return  new ResponseEntity<ProjectTask>(service.findProjectTaskByProjectSequence(projectSequence,backlog_id), HttpStatus.OK);
        //return service.findBacklogId(backlog_id);
    }
}

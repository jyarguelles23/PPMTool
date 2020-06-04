package kdc.developers.ppmtool.Controllers;

import kdc.developers.ppmtool.Entities.Project;
import kdc.developers.ppmtool.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService service;

    @PostMapping("")
    public ResponseEntity<Project>  createNewProject(@RequestBody Project project){

       return  new ResponseEntity<Project>(service.saveOrUpdate(project), HttpStatus.CREATED);
    }

}

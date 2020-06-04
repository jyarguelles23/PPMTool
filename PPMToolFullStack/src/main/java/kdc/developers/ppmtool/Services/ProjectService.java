package kdc.developers.ppmtool.Services;

import kdc.developers.ppmtool.Entities.Project;
import kdc.developers.ppmtool.Exceptions.ProjectIdException;
import kdc.developers.ppmtool.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


    ProjectRepository repository;

    public ProjectService (ProjectRepository rep){
        this.repository=rep;
    }

    public Project saveOrUpdate(Project p){
       try{
           return repository.save(p);
       }
       catch (Exception e){
           throw new ProjectIdException("Project Id '"+p.getProjectIdentifier().toUpperCase()+"' already exist");
       }

    }
}

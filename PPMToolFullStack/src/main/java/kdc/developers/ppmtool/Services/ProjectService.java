package kdc.developers.ppmtool.Services;

import kdc.developers.ppmtool.Entities.BackLog;
import kdc.developers.ppmtool.Entities.Project;
import kdc.developers.ppmtool.Exceptions.ProjectIdException;
import kdc.developers.ppmtool.Repositories.BackLogRepository;
import kdc.developers.ppmtool.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    ProjectRepository repository;
    BackLogRepository backrepo;

    public ProjectService (ProjectRepository rep,BackLogRepository repo){
        this.repository=rep;
        this.backrepo=repo;
    }

    public Project saveOrUpdate(Project p){
       try{
           if(p.getId() == null){
               BackLog backlog=new BackLog();
               p.setBacklog(backlog);
               backlog.setProject(p);
               backlog.setProjectidentifier(p.getProjectIdentifier());
           }
           else
           {
               p.setBacklog(backrepo.findByProjectidentifier(p.getProjectIdentifier()));
           }
           return repository.save(p);
       }
       catch (Exception e){
           throw new ProjectIdException("Project Id '"+p.getProjectIdentifier().toUpperCase()+"' already exist");
       }

    }

    public Project findProjectByIdentifier(String projectId){
        Project project=repository.findByProjectIdentifier(projectId);
        if(project == null){
            throw new ProjectIdException( "Id project '"+ projectId.toUpperCase()+"' is not in the system");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return repository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){

        Project project=repository.findByProjectIdentifier(projectId);
        if(project == null){
            throw new ProjectIdException( "Cannot delete with ID '"+ projectId.toUpperCase()+"' is not in the system");
        }
        repository.delete(project);
    }
}

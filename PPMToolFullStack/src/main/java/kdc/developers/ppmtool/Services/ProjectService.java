package kdc.developers.ppmtool.Services;

import kdc.developers.ppmtool.Entities.BackLog;
import kdc.developers.ppmtool.Entities.Project;
import kdc.developers.ppmtool.Entities.Usuario;
import kdc.developers.ppmtool.Exceptions.ProjectIdException;
import kdc.developers.ppmtool.Exceptions.ProjectNotFoundException;
import kdc.developers.ppmtool.Repositories.BackLogRepository;
import kdc.developers.ppmtool.Repositories.ProjectRepository;
import kdc.developers.ppmtool.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    ProjectRepository repository;
    BackLogRepository backrepo;
    UserRepository userrepo;

    public ProjectService (ProjectRepository rep,BackLogRepository repo,UserRepository u){
        this.repository=rep;
        this.backrepo=repo;
        this.userrepo=u;
    }

    public Project saveOrUpdate(Project p,String userName){
       try{
           Usuario user= userrepo.findByUsername(userName);
           p.setUser(user);
           p.setProjectLeader(user.getUsername());
           p.setProjectIdentifier(p.getProjectIdentifier().toUpperCase());

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

    public Project findProjectByIdentifier(String projectId,String username){
        Project project=repository.findByProjectIdentifier(projectId);
        if(project == null){
            throw new ProjectIdException( "Id project '"+ projectId.toUpperCase()+"' is not in the system");
        }

        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project not found in your account!");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(String username){
        return repository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectId,String username){
        repository.delete(findProjectByIdentifier(projectId,username));
    }
}

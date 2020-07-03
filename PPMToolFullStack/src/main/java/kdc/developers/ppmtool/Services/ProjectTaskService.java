package kdc.developers.ppmtool.Services;

import kdc.developers.ppmtool.Entities.BackLog;
import kdc.developers.ppmtool.Entities.ProjectTask;
import kdc.developers.ppmtool.Exceptions.ProjectIdException;
import kdc.developers.ppmtool.Exceptions.ProjectNotFoundException;
import kdc.developers.ppmtool.Repositories.BackLogRepository;
import kdc.developers.ppmtool.Repositories.ProjectRepository;
import kdc.developers.ppmtool.Repositories.ProjectTaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    ProjectTaskRepository repository;
    BackLogRepository backrepo;

    public ProjectTaskService(ProjectTaskRepository rep,BackLogRepository repo){
        this.repository=rep;
        this.backrepo=repo;
    }

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask p){
        try{

            //project!=null backlog exist
            BackLog backlog=backrepo.findByProjectidentifier(projectIdentifier);

            //project sequence
            Integer backLogSequence=backlog.getPTSequence();
            //Update sequence
            backLogSequence++;
            backlog.setPTSequence(backLogSequence);

            //set backlog to ptask
            p.setBacklog(backlog);
            //Add sequence to Ptask
            p.setProjectSequence(projectIdentifier+"-"+backLogSequence);
            p.setProjectIdentifier(projectIdentifier);

            //Initial priority
            //(p.getPriority() == 0) ||
            if((p.getPriority()==null) || (p.getPriority() == 0)){
                p.setPriority(3);
            }

            //Initial Status
            if((p.getStatus() == "") || (p.getStatus()==null)){
                p.setStatus("To_Do");
            }
            return repository.save(p);
        }
        catch (Exception e){
            throw new ProjectNotFoundException( "Cannot insert with because project Identifier "+projectIdentifier.toUpperCase()+"' is not in the system");
        }
    }

    public Iterable<ProjectTask> findBacklogId(String backlog_id){
        BackLog backlog=backrepo.findByProjectidentifier(backlog_id);
        if(backlog==null){
            throw new ProjectNotFoundException( "Project doesnt exist");
        }

        return repository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findProjectTaskByProjectSequence(String projectSequence,String backlog_id){
       //make sure to find an existing backlog
        BackLog backlog=backrepo.findByProjectidentifier(backlog_id);
        if(backlog==null){
            throw new ProjectNotFoundException( "Project bakclog doesnt exist");
        }
       //make sure that the task exist
        ProjectTask p=repository.findByProjectSequence(projectSequence);
        if(p==null){
            throw new ProjectNotFoundException( "Project task doesnt exist");
        }
       //make sure that the backlog and project id corresponds to the same project
        if(!p.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException( "Doesnt match the project identifier with the project task");
        }
         return p;

    }

     public ProjectTask  updateByProjectSequence(String sequence,String backlog_id,ProjectTask task){

        ProjectTask p =findProjectTaskByProjectSequence(sequence,backlog_id);

        return repository.save(task);

     }

     public void deleteProjectTaskByProjectSequence(String sequence,String backlog_id){
         ProjectTask p =findProjectTaskByProjectSequence(sequence,backlog_id);

         repository.delete(p);
     }
}

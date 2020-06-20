package kdc.developers.ppmtool.Services;

import kdc.developers.ppmtool.Entities.BackLog;
import kdc.developers.ppmtool.Entities.ProjectTask;
import kdc.developers.ppmtool.Exceptions.ProjectIdException;
import kdc.developers.ppmtool.Exceptions.ProjectNotFoundException;
import kdc.developers.ppmtool.Repositories.BackLogRepository;
import kdc.developers.ppmtool.Repositories.ProjectRepository;
import kdc.developers.ppmtool.Repositories.ProjectTaskRepository;
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

            //set backlog to ptask
            p.setBacklog(backlog);
            //project sequence
            Integer backLogSequence=backlog.getPTSequence();
            //Update sequence
            backLogSequence++;
            backlog.setPTSequence(backLogSequence);
            //Add sequence to Ptask
            p.setProjectSequence(projectIdentifier+"-"+backLogSequence);
            p.setProjectIdentifier(projectIdentifier);

            //Initial priority
            //(p.getPriority() == 0) ||
            if(p.getPriority()==null){
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


}

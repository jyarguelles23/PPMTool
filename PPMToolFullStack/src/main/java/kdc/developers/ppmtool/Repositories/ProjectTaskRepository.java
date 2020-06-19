package kdc.developers.ppmtool.Repositories;

import kdc.developers.ppmtool.Entities.ProjectTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectTaskRepository  extends CrudRepository<ProjectTask,Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
}

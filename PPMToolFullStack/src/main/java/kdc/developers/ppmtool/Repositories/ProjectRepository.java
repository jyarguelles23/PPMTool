package kdc.developers.ppmtool.Repositories;

import kdc.developers.ppmtool.Entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long> {

    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);
}



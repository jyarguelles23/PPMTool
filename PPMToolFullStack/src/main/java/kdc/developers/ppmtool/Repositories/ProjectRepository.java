package kdc.developers.ppmtool.Repositories;

import kdc.developers.ppmtool.Entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String identifier);
    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);
}



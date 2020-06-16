package kdc.developers.ppmtool.Repositories;

import kdc.developers.ppmtool.Entities.BackLog;
import org.springframework.data.repository.CrudRepository;

public interface BackLogRepository extends CrudRepository<BackLog,Long> {
    BackLog findByProjectidentifier(String p);
}

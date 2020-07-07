package kdc.developers.ppmtool.Repositories;

import kdc.developers.ppmtool.Entities.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<Usuario,Long> {

   Usuario findByUsername(String username );
   Usuario getById(Long id);
}

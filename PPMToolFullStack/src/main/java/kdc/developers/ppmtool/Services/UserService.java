package kdc.developers.ppmtool.Services;


import kdc.developers.ppmtool.Entities.Usuario;
import kdc.developers.ppmtool.Exceptions.UserNameException;
import kdc.developers.ppmtool.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
     UserRepository repo;


    public UserService(UserRepository rep){
        this.repo=rep;
    }

    public Usuario saveUser(Usuario newUsuario){

        try{
            newUsuario.setPassword(passwordEncoder.encode(newUsuario.getPassword()));
            //Username unique exception done!

            newUsuario.setConfirmPassword("");
            return  repo.save(newUsuario);
        }
        catch (Exception e){
           throw new UserNameException("The username "+newUsuario.getUsername()+" already exist");
        }
    }

}

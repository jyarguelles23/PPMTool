package kdc.developers.ppmtool.Services;


import kdc.developers.ppmtool.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

     UserRepository repo;
    public UserService(UserRepository rep){
        this.repo=rep;
    }

}

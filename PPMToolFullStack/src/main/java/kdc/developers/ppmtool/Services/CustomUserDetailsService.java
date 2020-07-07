package kdc.developers.ppmtool.Services;

import kdc.developers.ppmtool.Entities.Usuario;
import kdc.developers.ppmtool.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user=repo.findByUsername(username);
        if(user == null) new UsernameNotFoundException("User "+ username+ " not found!");
        return user;
    }

    @Transactional
    public Usuario loadUserById(Long id){
         Usuario user= repo.getById(id);
        if(user == null) new UsernameNotFoundException("User not found!");
        return user;
    }
}

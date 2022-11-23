package utfpr.edu.br.estacionamentoutfpr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.User;
import utfpr.edu.br.estacionamentoutfpr.repository.UserRepository;

@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }
}

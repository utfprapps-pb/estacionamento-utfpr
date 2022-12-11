package utfpr.edu.br.estacionamentoutfpr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;

@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private OperatorRepository operatorRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator operator = operatorRepository.findByUsername(username);
        if (operator == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return operator;
    }

    public boolean checkPermission(String role){
        Authentication authentication = this.getUserLogged();
        String roleAdmin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).filter(auth -> auth.equals(role)).findFirst().orElse(null);
    return (roleAdmin != null);
    }

    public Authentication getUserLogged(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Operator getOperatorLogged(){
        return  operatorRepository.findByUsername(this.getUserLogged().getPrincipal().toString());
    }


}

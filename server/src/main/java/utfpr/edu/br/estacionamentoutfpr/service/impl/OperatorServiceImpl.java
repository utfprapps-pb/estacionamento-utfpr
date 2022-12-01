package utfpr.edu.br.estacionamentoutfpr.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.AuthProvider;
import utfpr.edu.br.estacionamentoutfpr.model.Authority;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;
import utfpr.edu.br.estacionamentoutfpr.service.OperatorService;

import java.util.HashSet;
import java.util.UUID;

@Service
public class OperatorServiceImpl extends CrudServiceImpl<Operator, UUID>  implements OperatorService, UserDetailsService {

    OperatorRepository operatorRepository;

    AuthorityRepository authorityRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public OperatorServiceImpl(OperatorRepository operatorRepository, AuthorityRepository authorityRepository) {
        this.operatorRepository = operatorRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.authorityRepository = authorityRepository;
    }

    public Operator save(Operator operator) {
        operator.setPassword(bCryptPasswordEncoder.encode(operator.getPassword()));
        operator.setProvider(AuthProvider.local);
        operator.setUserAuthorities(new HashSet<>());
        operator.getUserAuthorities().add(authorityRepository.findById(1L).orElse(new Authority()));
        return operatorRepository.save(operator);
    }

    @Override
    protected JpaRepository<Operator, UUID> getRepository() {
        return operatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator usuario = operatorRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Login inválido!");
        }
        return usuario;
    }
}

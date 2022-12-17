package utfpr.edu.br.estacionamentoutfpr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import utfpr.edu.br.estacionamentoutfpr.security.AuthUserService;
import utfpr.edu.br.estacionamentoutfpr.service.OperatorService;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class OperatorServiceImpl extends CrudServiceImpl<Operator, UUID>  implements OperatorService, UserDetailsService {

    @Autowired
    AuthUserService authUserService;
    @Autowired
    OperatorRepository operatorRepository;

    AuthorityRepository authorityRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public OperatorServiceImpl(OperatorRepository operatorRepository, AuthorityRepository authorityRepository) {
        this.operatorRepository = operatorRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.authorityRepository = authorityRepository;
    }

    public Operator save(Operator operator) {
        if(operator.getId() == null) {
            operator.setPassword(bCryptPasswordEncoder.encode(operator.getPassword()));
            operator.setProvider(AuthProvider.local);
            operator.setUserAuthorities(new HashSet<>());
            operator.getUserAuthorities().add(authorityRepository.findById(1L).orElse(new Authority()));
            this.loadUserByUsername(operator.getUsername());
        }
        return operatorRepository.save(operator);
    }

    @Override
    protected JpaRepository<Operator, UUID> getRepository() {
        return operatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator usuario = operatorRepository.findByUsername(username);

        if (usuario != null) {
            throw new UsernameNotFoundException("Login inválido!");
        }
        return usuario;
    }
    @Override
    public List<Operator> findAll() {
        if(authUserService.checkPermission("ROLE_ADMIN")){
            return super.findAll();
        }
        return operatorRepository.findAllByUsername(authUserService.getUserLogged().getPrincipal().toString());
    }

    @Override
    public List<Operator> findAll(Sort sort) {
        if(authUserService.checkPermission("ROLE_ADMIN")){
            return super.findAll(sort);
        }
        return operatorRepository.findAllByUsername(sort, authUserService.getUserLogged().getPrincipal().toString());

    }

    @Override
    public Page<Operator> findAll(Pageable pageable) {
        if(authUserService.checkPermission("ROLE_ADMIN")){
            return super.findAll(pageable);
        }
        return operatorRepository.findAllByUsername(pageable, authUserService.getUserLogged().getPrincipal().toString());
    }

}

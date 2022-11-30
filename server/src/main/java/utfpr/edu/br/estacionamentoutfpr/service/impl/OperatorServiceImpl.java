package utfpr.edu.br.estacionamentoutfpr.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;
import utfpr.edu.br.estacionamentoutfpr.service.OperatorService;

import java.util.UUID;

@Service
public class OperatorServiceImpl extends CrudServiceImpl<Operator, UUID>  implements OperatorService {

    OperatorRepository operatorRepository;

    AuthorityRepository authorityRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public OperatorServiceImpl(OperatorRepository operatorRepository, AuthorityRepository authorityRepository) {
        this.operatorRepository = operatorRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.authorityRepository = authorityRepository;
    }

    @Override
    protected JpaRepository<Operator, UUID> getRepository() {
        return operatorRepository;
    }
}

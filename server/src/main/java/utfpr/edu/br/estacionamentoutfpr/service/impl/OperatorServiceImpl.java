package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.AuthProvider;
import utfpr.edu.br.estacionamentoutfpr.model.Authority;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;
import utfpr.edu.br.estacionamentoutfpr.service.OperatorService;

import java.util.HashSet;

@Service
public class OperatorServiceImpl implements OperatorService {

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

}

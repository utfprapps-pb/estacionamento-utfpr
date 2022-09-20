package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;
import utfpr.edu.br.estacionamentoutfpr.service.OperatorService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OperatorServiceImpl extends CrudServiceImpl<Operator, UUID> implements OperatorService {

    private final OperatorRepository operatorRepository;
    @Override
    protected JpaRepository<Operator, UUID> getRepository() {
        return this.operatorRepository;
    }
}

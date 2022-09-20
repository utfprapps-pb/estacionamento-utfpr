package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Infraction;
import utfpr.edu.br.estacionamentoutfpr.repository.InfractionRepository;
import utfpr.edu.br.estacionamentoutfpr.service.InfractionService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfractionServiceImpl extends CrudServiceImpl<Infraction, UUID> implements InfractionService {

    private final InfractionRepository infractionRepository;
    @Override
    protected JpaRepository<Infraction, UUID> getRepository() {
        return this.infractionRepository;
    }
}

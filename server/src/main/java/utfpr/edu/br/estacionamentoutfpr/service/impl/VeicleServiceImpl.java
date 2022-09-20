package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Veicle;
import utfpr.edu.br.estacionamentoutfpr.repository.VeicleRepository;
import utfpr.edu.br.estacionamentoutfpr.service.VeicleService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VeicleServiceImpl extends CrudServiceImpl<Veicle, UUID> implements VeicleService {

    private final VeicleRepository veicleRepository;

    @Override
    protected JpaRepository<Veicle, UUID> getRepository() {
        return this.veicleRepository;
    }
}

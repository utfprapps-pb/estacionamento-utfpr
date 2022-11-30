package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Vehicle;
import utfpr.edu.br.estacionamentoutfpr.repository.VeicleRepository;
import utfpr.edu.br.estacionamentoutfpr.service.VehicleService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl extends CrudServiceImpl<Vehicle, UUID> implements VehicleService {

    private final VeicleRepository veicleRepository;

    @Override
    protected JpaRepository<Vehicle, UUID> getRepository() {
        return this.veicleRepository;
    }
}

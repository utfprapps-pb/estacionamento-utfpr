package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.RequestStatus;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequest;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.StickerRequestRepository;
import utfpr.edu.br.estacionamentoutfpr.service.StickerRequestService;
import utfpr.edu.br.estacionamentoutfpr.service.VehicleService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StickerRequestServiceImpl extends CrudServiceImpl<StickerRequest, UUID> implements StickerRequestService {

    private final StickerRequestRepository stickerRequestRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    VehicleService vehicleService;
    @Override
    protected JpaRepository<StickerRequest, UUID> getRepository() {
        return this.stickerRequestRepository;
    }


    @Override
    public StickerRequest save(StickerRequest entity) {

        if(entity.getStatus() == null){
            entity.setStatus(RequestStatus.IN_ANALYSIS);
        }
        entity.setVehicle(vehicleService.save(entity.getVehicle()));
        return super.save(entity);
    }
}

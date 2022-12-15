package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.model.RequestStatus;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequest;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.StickerRequestRepository;
import utfpr.edu.br.estacionamentoutfpr.security.AuthUserService;
import utfpr.edu.br.estacionamentoutfpr.service.StickerRequestService;
import utfpr.edu.br.estacionamentoutfpr.service.VehicleService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StickerRequestServiceImpl extends CrudServiceImpl<StickerRequest, UUID> implements StickerRequestService {

    private final StickerRequestRepository stickerRequestRepository;

    @Autowired
    AuthUserService authUserService;
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

        if(entity.getId() == null){

        }
        return super.save(entity);
    }

    @Override
    public List<StickerRequest> findAll() {
        if(authUserService.checkPermission("ROLE_ADMIN")){
            return super.findAll();
        }
        return stickerRequestRepository.findStickerRequestByOperatorRequesterUsername(authUserService.getUserLogged().getPrincipal().toString());
    }

    @Override
    public List<StickerRequest> findAll(Sort sort) {
        if(authUserService.checkPermission("ROLE_ADMIN")){
            return super.findAll(sort);
        }
        return stickerRequestRepository.findStickerRequestByOperatorRequesterUsername(sort, authUserService.getUserLogged().getPrincipal().toString());

    }

    @Override
    public Page<StickerRequest> findAll(Pageable pageable) {
        if(authUserService.checkPermission("ROLE_ADMIN")){
            return super.findAll(pageable);
        }
        return stickerRequestRepository.findStickerRequestByOperatorRequesterUsername(pageable, authUserService.getUserLogged().getPrincipal().toString());
    }

    @Override
    public StickerRequest findOne(UUID uuid) {
        return super.findOne(uuid);
    }
}

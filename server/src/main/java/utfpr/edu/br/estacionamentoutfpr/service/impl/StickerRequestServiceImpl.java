package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequest;
import utfpr.edu.br.estacionamentoutfpr.repository.StickerRequestRepository;
import utfpr.edu.br.estacionamentoutfpr.service.StickerRequestService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StickerRequestServiceImpl extends CrudServiceImpl<StickerRequest, UUID> implements StickerRequestService {

    private final StickerRequestRepository stickerRequestRepository;
    @Override
    protected JpaRepository<StickerRequest, UUID> getRepository() {
        return this.stickerRequestRepository;
    }
}

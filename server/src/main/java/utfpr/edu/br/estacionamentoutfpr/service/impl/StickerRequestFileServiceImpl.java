package utfpr.edu.br.estacionamentoutfpr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequestFile;
import utfpr.edu.br.estacionamentoutfpr.repository.StickerRequestFileRepository;
import utfpr.edu.br.estacionamentoutfpr.service.StickerRequestFileService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StickerRequestFileServiceImpl extends CrudServiceImpl<StickerRequestFile, UUID> implements StickerRequestFileService {

    private final StickerRequestFileRepository stickerRequestFileRepository;
    @Override
    protected JpaRepository<StickerRequestFile, UUID> getRepository() {
        return this.stickerRequestFileRepository;
    }
}

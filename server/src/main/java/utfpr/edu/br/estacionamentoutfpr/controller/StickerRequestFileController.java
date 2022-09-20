package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequestFile;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.StickerRequestFileService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("stickerRequestFile")
public class StickerRequestFileController extends CrudController<StickerRequestFile, UUID> {

    private final StickerRequestFileService stickerRequestFileService;

    @Override
    protected CrudService<StickerRequestFile, UUID> getService() {
        return this.stickerRequestFileService;
    }
}

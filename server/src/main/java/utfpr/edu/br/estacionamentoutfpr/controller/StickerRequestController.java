package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequest;
import utfpr.edu.br.estacionamentoutfpr.model.Vehicle;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.StickerRequestService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("stickerRequest")
public class StickerRequestController extends CrudController<StickerRequest, UUID> {

    private final StickerRequestService stickerRequestService;
    @Override
    protected CrudService<StickerRequest, UUID> getService() {
        return this.stickerRequestService;
    }
}

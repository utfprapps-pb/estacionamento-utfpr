package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.edu.br.estacionamentoutfpr.model.Infraction;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.InfractionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("infraction")
public class InfractionController extends CrudController<Infraction, UUID> {

    private final InfractionService infractionService;
    @Override
    protected CrudService<Infraction, UUID> getService() {
        return this.infractionService;
    }
}

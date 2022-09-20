package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.edu.br.estacionamentoutfpr.model.Veicle;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.VeicleService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("veicle")
public class VeicleController extends CrudController<Veicle, UUID> {

    private final VeicleService veicleService;
    @Override
    protected CrudService<Veicle, UUID> getService() {
        return this.veicleService;
    }
}

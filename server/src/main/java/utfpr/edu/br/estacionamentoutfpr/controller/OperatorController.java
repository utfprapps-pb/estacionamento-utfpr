package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import utfpr.edu.br.estacionamentoutfpr.model.Infraction;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.model.Vehicle;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.OperatorService;
import utfpr.edu.br.estacionamentoutfpr.service.VehicleService;
import utfpr.edu.br.estacionamentoutfpr.shared.GenericResponse;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class OperatorController extends CrudController<Operator, UUID> {

    private final OperatorService operatorService;
    @Override
    protected CrudService<Operator, UUID> getService() {
        return this.operatorService;
    }

}

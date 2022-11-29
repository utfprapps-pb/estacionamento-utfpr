package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.OperatorService;
import utfpr.edu.br.estacionamentoutfpr.shared.GenericResponse;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class OperatorController  {

    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @PostMapping
    GenericResponse createOperator(@Valid @RequestBody Operator operator) {
        operatorService.save(operator);
        return new GenericResponse("Registro salvo.");
    }
}

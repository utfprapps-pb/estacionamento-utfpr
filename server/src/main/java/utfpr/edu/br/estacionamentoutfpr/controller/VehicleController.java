package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utfpr.edu.br.estacionamentoutfpr.model.Vehicle;
import utfpr.edu.br.estacionamentoutfpr.model.dto.CarBrandModelDTO;
import utfpr.edu.br.estacionamentoutfpr.model.dto.ModelDTO;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.VehicleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("vehicle")
public class VehicleController extends CrudController<Vehicle, UUID> {

    private final VehicleService vehicleService;

    @GetMapping("listCarBrands")
    public List<CarBrandModelDTO> getCarBrands() { return vehicleService.getCarBrands();}

    @GetMapping("listCarModels")
    public ModelDTO getCarModels(@RequestParam("brandCode") int brandCode) { return vehicleService.getCarModels(brandCode);}

    @Override
    protected CrudService<Vehicle, UUID> getService() {
        return this.vehicleService;
    }
}

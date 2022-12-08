package utfpr.edu.br.estacionamentoutfpr.service;

import utfpr.edu.br.estacionamentoutfpr.model.Vehicle;
import utfpr.edu.br.estacionamentoutfpr.model.dto.CarBrandModelDTO;
import utfpr.edu.br.estacionamentoutfpr.model.dto.ModelDTO;

import java.util.List;
import java.util.UUID;

public interface VehicleService extends CrudService<Vehicle, UUID> {

    List<CarBrandModelDTO> getCarBrands();
    ModelDTO getCarModels(int brandCode);
}

package utfpr.edu.br.estacionamentoutfpr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.Vehicle;
import utfpr.edu.br.estacionamentoutfpr.model.dto.CarBrandModelDTO;
import utfpr.edu.br.estacionamentoutfpr.model.dto.ModelDTO;
import utfpr.edu.br.estacionamentoutfpr.repository.VeicleRepository;
import utfpr.edu.br.estacionamentoutfpr.service.VehicleService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl extends CrudServiceImpl<Vehicle, UUID> implements VehicleService {

    private final VeicleRepository veicleRepository;

    @Override
    protected JpaRepository<Vehicle, UUID> getRepository() {
        return this.veicleRepository;
    }

    @Override
    public List<CarBrandModelDTO> getCarBrands() {
        List<CarBrandModelDTO> brands = new ArrayList<CarBrandModelDTO>();
        CarBrandModelDTO brand = new CarBrandModelDTO();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CarBrandModelDTO.URL_BRAND))
                .header("Content-Type", "application/json")
                .header("chave" , CarBrandModelDTO.KEY)
                .POST(HttpRequest.BodyPublishers.ofString(CarBrandModelDTO.JSON_REFERENCE_TABLE +
                        CarBrandModelDTO.REFERENCE_TABLE +
                        CarBrandModelDTO.JSON_VEICLE_TYPE + CarBrandModelDTO.VEICLE_TYPE + "}"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    brand.setJson(response.replace("Label", "label").replace("Value","value"));
                })
                .join();
        try {
            brands = new ObjectMapper().readValue(brand.getJson(),  new TypeReference<List<CarBrandModelDTO>>(){});
            brand.setJson(null);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return brands;
    }

    @Override
    public ModelDTO getCarModels(int brandCode) {
        ModelDTO models = new ModelDTO();
        CarBrandModelDTO model = new CarBrandModelDTO();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CarBrandModelDTO.URL_MODEL))
                .header("Content-Type", "application/json")
                .header("chave" , CarBrandModelDTO.KEY)
                .POST(HttpRequest.BodyPublishers.ofString(CarBrandModelDTO.JSON_REFERENCE_TABLE +
                        CarBrandModelDTO.REFERENCE_TABLE +
                        CarBrandModelDTO.JSON_VEICLE_TYPE + CarBrandModelDTO.VEICLE_TYPE +
                        CarBrandModelDTO.JSON_BRAND_CODE + brandCode + "}"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    model.setJson(response.replace("Label", "label").replace("Value","value")
                            .replace("Modelos", "carBrandModelDTO").replace("Anos", "anos"));
                })
                .join();
        try {
            models = new ObjectMapper().readValue(model.getJson(),  new TypeReference<ModelDTO>(){});
            model.setJson(null);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return models;
    }

}

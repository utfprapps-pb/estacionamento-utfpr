package utfpr.edu.br.estacionamentoutfpr.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CarBrandModelDTO {

    public static final String URL_BRAND = "https://fipe.contrateumdev.com.br/api/ConsultarMarcas";
    public static final String URL_MODEL = "https://fipe.contrateumdev.com.br/api/ConsultarModelos";
    public static final String JSON_REFERENCE_TABLE = "{\"codigoTabelaReferencia\": ";
    public static final String JSON_VEICLE_TYPE = ",\"codigoTipoVeiculo\": ";
    public static final String JSON_BRAND_CODE = ",\"codigoMarca\": ";
    public static final String KEY = "$2y$10$8IAZn7HKq7QJWbh37N3GOOeRVv";
    public static final int REFERENCE_TABLE = 289;
    public static final int VEICLE_TYPE = 1;

    @JsonIgnore
    private String json = "";

    private String label;
    private int value;
}
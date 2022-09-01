package utfpr.edu.br.estacionamentoutfpr.model;

public enum Profile {

    ADMINISTRATIVE("Administrativo"),
    REQUESTER("Solicitante"),
    SERVICE_PROVIDER("Prestador de Serviço");

    private String description;

    Profile (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

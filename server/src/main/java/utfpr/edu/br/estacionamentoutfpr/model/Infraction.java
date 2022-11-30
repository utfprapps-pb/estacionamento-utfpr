package utfpr.edu.br.estacionamentoutfpr.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Infraction {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull(message = "O veículo não pode ser nulo")
    @ManyToOne
    private Vehicle vehicle;

    @NotNull(message = "A descrição não pode ser nula")
    private String description;

    private String explanation;

    private String documentFileName;
}

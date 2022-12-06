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
public class StickerRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull(message = "O nome da solicitação não pode ser nulo")
    private String name;

    @NotNull(message = "O usuário não pode ser nulo")
    @ManyToOne
    private Operator operatorRequester;

    @NotNull(message = "O veículo não pode ser nulo")
    @OneToOne
    private Vehicle vehicle;

    @ManyToOne
    private Operator operatorApprover;

    @NotNull(message = "O status não pode ser nulo")
    private RequestStatus status;

    private String requesterMessage;

    private String approverMessage;

    private Integer stickerNumber;
}

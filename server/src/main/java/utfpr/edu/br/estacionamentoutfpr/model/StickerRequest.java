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
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull
    @ManyToOne
    private User userRequester;

    @NotNull
    @OneToOne
    private Veicle veicle;

    @ManyToOne
    private User userApprover;

    @NotNull
    private RequestStatus status;

    private String requesterMessage;

    private String approverMessage;

    private Integer stickerNumber;
}

package utfpr.edu.br.estacionamentoutfpr.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.model.User;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {

    private UUID id;
    private String name;
    private String username;

    public UserDTO(Operator operator) {
        this.id = operator.getId();
        this.name = operator.getName();
        this.username = operator.getUsername();
    }
}

package utfpr.edu.br.estacionamentoutfpr.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import utfpr.edu.br.estacionamentoutfpr.model.User;

@Data
@NoArgsConstructor
public class UserDTO {

    private long id;
    private String displayName;
    private String username;

    public UserDTO(User user) {
        this.id = user.getId();
        this.displayName = user.getDisplayName();
        this.username = user.getUsername();
    }
}

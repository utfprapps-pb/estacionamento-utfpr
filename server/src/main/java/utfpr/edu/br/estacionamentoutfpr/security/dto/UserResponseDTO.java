package utfpr.edu.br.estacionamentoutfpr.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String name;
    private String username;
    private Set<AuthorityResponseDTO> authorities;

    public UserResponseDTO(Operator operator) {
        this.name = operator.getName();
        this.username = operator.getUsername();
        this.authorities = new HashSet<>();
        for (GrantedAuthority authority: operator.getAuthorities()) {
            authorities.add( new AuthorityResponseDTO(authority.getAuthority()) );
        }
    }

}

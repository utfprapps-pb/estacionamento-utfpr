package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import utfpr.edu.br.estacionamentoutfpr.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}

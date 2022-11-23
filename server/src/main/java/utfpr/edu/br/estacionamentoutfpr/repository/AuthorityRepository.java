package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}

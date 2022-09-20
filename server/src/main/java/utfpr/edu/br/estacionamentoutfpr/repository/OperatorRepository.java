package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;

import java.util.UUID;

public interface OperatorRepository extends JpaRepository<Operator, UUID> {
    Operator findByUsername(String username);
}

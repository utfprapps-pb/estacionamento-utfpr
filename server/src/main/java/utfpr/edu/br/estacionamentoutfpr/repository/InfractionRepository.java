package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.Infraction;

import java.util.UUID;

public interface InfractionRepository extends JpaRepository<Infraction, UUID> {
}

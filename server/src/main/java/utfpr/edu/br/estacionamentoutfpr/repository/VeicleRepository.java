package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.Vehicle;

import java.util.UUID;

public interface VeicleRepository extends JpaRepository<Vehicle, UUID> {
}

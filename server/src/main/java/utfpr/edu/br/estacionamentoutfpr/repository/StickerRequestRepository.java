package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequest;

import java.util.UUID;

public interface StickerRequestRepository extends JpaRepository<StickerRequest, UUID> {
}

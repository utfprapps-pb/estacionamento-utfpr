package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequestFile;

import java.util.UUID;

public interface StickerRequestFileRepository extends JpaRepository<StickerRequestFile, UUID> {
}

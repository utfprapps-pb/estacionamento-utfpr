package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequest;

import java.util.List;
import java.util.UUID;

public interface StickerRequestRepository extends JpaRepository<StickerRequest, UUID> {

    List<StickerRequest> findStickerRequestByOperatorRequesterUsername(String userName);

    List<StickerRequest> findStickerRequestByOperatorRequesterUsername(Sort sort, String userName);

    Page<StickerRequest> findStickerRequestByOperatorRequesterUsername(Pageable page, String userName);
}

package utfpr.edu.br.estacionamentoutfpr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;

import java.util.List;
import java.util.UUID;

public interface OperatorRepository extends JpaRepository<Operator, UUID> {
    Operator findByUsername(String username);

    List<Operator> findAllByUsername(String nome);
    List<Operator> findAllByUsername(Sort sort, String nome);


    Page<Operator> findAllByUsername(Pageable pageable, String nome);

}

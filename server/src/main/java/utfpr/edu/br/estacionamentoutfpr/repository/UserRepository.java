package utfpr.edu.br.estacionamentoutfpr.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.estacionamentoutfpr.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

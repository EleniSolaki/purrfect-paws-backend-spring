package gr.Solaki.AnimalAdoption.repository;

import gr.Solaki.AnimalAdoption.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long> {

    User findOneByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    User findUserById(Long id);
}

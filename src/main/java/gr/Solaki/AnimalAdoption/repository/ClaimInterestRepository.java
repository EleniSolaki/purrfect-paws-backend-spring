package gr.Solaki.AnimalAdoption.repository;

import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.model.ClaimInterest;
import gr.Solaki.AnimalAdoption.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimInterestRepository extends JpaRepository<ClaimInterest,Long> {

    boolean existsByUserAndAnimal(User user, Animal animal);
    boolean existsByUser_IdAndAnimal_Id(Long userId, Long animalId);

}

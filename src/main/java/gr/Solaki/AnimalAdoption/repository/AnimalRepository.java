package gr.Solaki.AnimalAdoption.repository;

import gr.Solaki.AnimalAdoption.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE a.breed = ?1")
    List<Animal> findAllByBreed(String breed);

    @Query("SELECT a FROM Animal a WHERE a.age = ?1")
    List<Animal> findAllByAge(String age);

    @Query("SELECT a FROM Animal a WHERE a.gender = ?1")
    List<Animal> findAllByGender(String gender);

    Animal findAnimalById(Long id);

}

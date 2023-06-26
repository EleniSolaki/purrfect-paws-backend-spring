package gr.Solaki.AnimalAdoption.repository;

import gr.Solaki.AnimalAdoption.model.FavoriteAnimal;
import gr.Solaki.AnimalAdoption.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteAnimalRepository extends JpaRepository<FavoriteAnimal,Long> {

    @Modifying
    @Query("DELETE FROM FavoriteAnimal fa WHERE fa.user.id = ?1 AND fa.animal.id = ?2")
    void deleteByUserIdAndAnimalId(Long userId, Long animalId);

    List<FavoriteAnimal> findByUser(User user);

    @Modifying
    @Query(value = "INSERT INTO favoriteanimals(user_id, animal_id) " +
            "SELECT :userId, :animalId " +
            "FROM animals a " +
            "WHERE a.id = :animalId " +
            "AND NOT EXISTS (SELECT 1 FROM favoriteanimals fa " +
            "WHERE fa.user_id = :userId " +
            "AND fa.animal_id = :animalId)",
            nativeQuery = true)
    int saveFavoriteAnimalIfNotExists(@Param("userId") Long userId, @Param("animalId") Long animalId);
}

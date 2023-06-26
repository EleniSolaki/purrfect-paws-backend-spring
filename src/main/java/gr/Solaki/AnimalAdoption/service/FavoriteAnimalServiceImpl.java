package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.dto.FavoriteAnimalDTO;
import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.model.FavoriteAnimal;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.repository.AnimalRepository;
import gr.Solaki.AnimalAdoption.repository.FavoriteAnimalRepository;
import gr.Solaki.AnimalAdoption.repository.UserRepository;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityAlreadyExists;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteAnimalServiceImpl implements IFavoriteAnimalService {

    private final FavoriteAnimalRepository favoriteAnimalRepository;
    private AnimalRepository animalRepository;
    private UserRepository userRepository;

    @Autowired
    public FavoriteAnimalServiceImpl(FavoriteAnimalRepository favoriteAnimalRepository, AnimalRepository animalRepository, UserRepository userRepository) {
        this.favoriteAnimalRepository = favoriteAnimalRepository;
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public FavoriteAnimalDTO saveToFavorites(Long userId, Long animalId) throws EntityNotFoundException, EntityAlreadyExists {
        int rowsAffected = favoriteAnimalRepository.saveFavoriteAnimalIfNotExists(userId, animalId);
        if (rowsAffected > 0) {

            Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new EntityNotFoundException(Animal.class, animalId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, userId));

        FavoriteAnimal favoriteAnimal = new FavoriteAnimal();
        favoriteAnimal.setUser(user);
        favoriteAnimal.setAnimal(animal);

        FavoriteAnimalDTO favoriteAnimalDTO = new FavoriteAnimalDTO();
        favoriteAnimalDTO.setUser(user);
        favoriteAnimalDTO.setAnimal(animal);
        return favoriteAnimalDTO;
        } else {
           throw new EntityAlreadyExists(FavoriteAnimal.class);
        }
    }

    @Transactional
    @Override
    public void removeFavoriteAnimal(Long userId, Long animalId) {
        favoriteAnimalRepository.deleteByUserIdAndAnimalId(userId, animalId);
    }

    @Override
    public List<Animal> getAllAnimalsByUser(User user) {
        List<FavoriteAnimal> favorites = favoriteAnimalRepository.findByUser(user);
        return favorites.stream().map(FavoriteAnimal::getAnimal).collect(Collectors.toList());
    }
}

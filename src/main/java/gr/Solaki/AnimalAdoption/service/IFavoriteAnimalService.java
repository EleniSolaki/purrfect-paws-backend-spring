package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.dto.AnimalDTO;
import gr.Solaki.AnimalAdoption.dto.FavoriteAnimalDTO;
import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.model.FavoriteAnimal;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityAlreadyExists;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IFavoriteAnimalService {
    FavoriteAnimalDTO saveToFavorites(Long userId, Long animalId) throws EntityNotFoundException, EntityAlreadyExists;

    void removeFavoriteAnimal(Long userId, Long animalId);

    List<Animal> getAllAnimalsByUser(User user);
}
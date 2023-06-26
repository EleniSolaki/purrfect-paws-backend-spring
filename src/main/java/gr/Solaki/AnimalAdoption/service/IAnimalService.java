package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IAnimalService {
    Animal getAnimalById(Long id)throws EntityNotFoundException;
    List<Animal> getAllAnimals() throws EntityNotFoundException;
    List<Animal> getAnimalsByBreed(String breed) throws EntityNotFoundException;
    List<Animal> getAnimalsByAge(String age) throws EntityNotFoundException;
    List<Animal> getAnimalsByGender(String gender) throws EntityNotFoundException;
    String getAnimalNameById(Long id) throws EntityNotFoundException;


}

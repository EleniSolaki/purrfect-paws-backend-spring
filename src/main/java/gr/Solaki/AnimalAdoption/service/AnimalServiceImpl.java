package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.repository.AnimalRepository;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements IAnimalService{

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository){
        this.animalRepository=animalRepository;
    }

    @Override
    public Animal getAnimalById(Long id) throws EntityNotFoundException{
        Optional<Animal> animal;
        animal = animalRepository.findById(id);
        if(animal.isEmpty()) throw new EntityNotFoundException(Animal.class,0L);
        return animal.get();
    }

    @Override
    public List<Animal> getAllAnimals() throws EntityNotFoundException {
        List<Animal> animals;
        animals = animalRepository.findAll();
        if(animals.size() == 0) throw new EntityNotFoundException(Animal.class, 0l);

        return animals;
    }

    @Override
    public List<Animal> getAnimalsByBreed(String breed) throws EntityNotFoundException {
        List<Animal> animals;
        animals = animalRepository.findAllByBreed(breed);
        if(animals.size() == 0) throw new EntityNotFoundException(Animal.class, 0l);
        return animals;
    }

    @Override
    public List<Animal> getAnimalsByAge(String age) throws EntityNotFoundException {
        List<Animal> animals;
        animals = animalRepository.findAllByAge(age);
        if(animals.size() == 0) throw new EntityNotFoundException(Animal.class, 0l);
        return animals;
    }

    @Override
    public List<Animal> getAnimalsByGender(String gender) throws EntityNotFoundException {
        List<Animal> animals;
        animals = animalRepository.findAllByGender(gender);
        if(animals.size() == 0) throw new EntityNotFoundException(Animal.class, 0l);
        return animals;
    }

    @Override
    public String getAnimalNameById(Long id) throws EntityNotFoundException {
        Animal animal = animalRepository.findAnimalById(id);

        if (animal == null) throw new EntityNotFoundException("Animal not found");
        Animal animalName = animalRepository.findAnimalById(id);

        return animalName.getName();
    }
}

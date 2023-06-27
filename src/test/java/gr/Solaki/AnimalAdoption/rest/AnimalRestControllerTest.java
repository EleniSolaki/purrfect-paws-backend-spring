package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.dto.AnimalDTO;
import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.service.AnimalServiceImpl;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalRestControllerTest {

    private AnimalServiceImpl animalService = mock(AnimalServiceImpl.class);
    private String baseUrl = "http://localhost:8080/";
    private AnimalRestController animalRestController = new AnimalRestController(animalService, baseUrl);


    @Test
    void getAllAnimals_ReturnsListOfAnimalDTOs() throws EntityNotFoundException {
        List<Animal> animals = new ArrayList<>();
        Animal animal1 = new Animal();
        animal1.setId(1L);
        animal1.setAge("7 years old");
        animal1.setBreed("Aegean");
        animal1.setName("Booloobina");
        animal1.setGender("Female");
        animal1.setImage("Booloobina.jpg");
        animal1.setDescription("Chill, absent minded lady");
        animals.add(animal1);

        when(animalService.getAllAnimals()).thenReturn(animals);

        ResponseEntity<List<AnimalDTO>> response = animalRestController.getAllAnimals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> animalDTOs = response.getBody();
        assertEquals(1, animalDTOs.size());

        AnimalDTO animalDTO = animalDTOs.get(0);
        assertEquals(animal1.getId(), animalDTO.getId());
        assertEquals(animal1.getAge(), animalDTO.getAge());
        assertEquals(animal1.getBreed(), animalDTO.getBreed());
        assertEquals(animal1.getName(), animalDTO.getName());
        assertEquals(animal1.getGender(), animalDTO.getGender());
        assertEquals(baseUrl + animal1.getImage(), animalDTO.getImage());
        assertEquals(animal1.getDescription(), animalDTO.getDescription());

        verify(animalService).getAllAnimals();
        verifyNoMoreInteractions(animalService);
    }
    @Test
    void getAllAnimals_EntityNotFoundException_ReturnsBadRequestResponse() throws EntityNotFoundException {
        when(animalService.getAllAnimals()).thenThrow(EntityNotFoundException.class);

        ResponseEntity<List<AnimalDTO>> response = animalRestController.getAllAnimals();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(animalService).getAllAnimals();
        verifyNoMoreInteractions(animalService);
    }


    @Test
    void getAllAnimalsByBreed_ValidBreed_ReturnsListOfAnimalDTOs() throws EntityNotFoundException {
        String breed = "Aegean";
        List<Animal> animals = new ArrayList<>();
        Animal animal1 = new Animal();
        animal1.setId(1L);
        animal1.setAge("7 years old");
        animal1.setBreed(breed);
        animal1.setName("Booloobina");
        animal1.setGender("Female");
        animal1.setImage("Booloobina.jpg");
        animal1.setDescription("Chill, absent minded lady");
        animals.add(animal1);

        when(animalService.getAnimalsByBreed(breed)).thenReturn(animals);

        ResponseEntity<List<AnimalDTO>> response = animalRestController.getAllAnimalsByBreed(breed);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> animalDTOs = response.getBody();
        assertEquals(1, animalDTOs.size());
        AnimalDTO animalDTO = animalDTOs.get(0);
        assertEquals(animal1.getId(), animalDTO.getId());
        assertEquals(animal1.getAge(), animalDTO.getAge());
        assertEquals(animal1.getBreed(), animalDTO.getBreed());
        assertEquals(animal1.getName(), animalDTO.getName());
        assertEquals(animal1.getGender(), animalDTO.getGender());
        assertEquals(baseUrl + animal1.getImage(), animalDTO.getImage());
        assertEquals(animal1.getDescription(), animalDTO.getDescription());

        verify(animalService).getAnimalsByBreed(breed);
        verifyNoMoreInteractions(animalService);
    }

    @Test
    void getAllAnimalsByBreed_EntityNotFoundException_ReturnsBadRequestResponse() throws EntityNotFoundException {
        String breed = "Aegean";
        when(animalService.getAnimalsByBreed(breed)).thenThrow(EntityNotFoundException.class);

        ResponseEntity<List<AnimalDTO>> response = animalRestController.getAllAnimalsByBreed(breed);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(animalService).getAnimalsByBreed(breed);
        verifyNoMoreInteractions(animalService);
    }
    ////

    @Test
    void getAllAnimalsByAge_ValidAge_ReturnsListOfAnimalDTOs() throws EntityNotFoundException {
        String age = "7 years old";
        List<Animal> animals = new ArrayList<>();
        Animal animal1 = new Animal();
        animal1.setId(1L);
        animal1.setAge(age);
        animal1.setBreed("Aegean");
        animal1.setName("Booloobina");
        animal1.setGender("Female");
        animal1.setImage("Booloobina.jpg");
        animal1.setDescription("Chill, absent minded lady");
        animals.add(animal1);

        when(animalService.getAnimalsByAge(age)).thenReturn(animals);

        ResponseEntity<List<AnimalDTO>> response = animalRestController.getAllAnimalsByAge(age);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnimalDTO> animalDTOs = response.getBody();
        assertEquals(1, animalDTOs.size());
        AnimalDTO animalDTO = animalDTOs.get(0);
        assertEquals(animal1.getId(), animalDTO.getId());
        assertEquals(animal1.getAge(), animalDTO.getAge());
        assertEquals(animal1.getBreed(), animalDTO.getBreed());
        assertEquals(animal1.getName(), animalDTO.getName());
        assertEquals(animal1.getGender(), animalDTO.getGender());
        assertEquals(baseUrl + animal1.getImage(), animalDTO.getImage());
        assertEquals(animal1.getDescription(), animalDTO.getDescription());

        verify(animalService).getAnimalsByAge(age);
        verifyNoMoreInteractions(animalService);
    }

    }
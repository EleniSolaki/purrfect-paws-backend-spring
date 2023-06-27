package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.service.IFavoriteAnimalService;
import gr.Solaki.AnimalAdoption.service.IUserService;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FavoriteAnimalRestControllerTest {

    private IFavoriteAnimalService favoriteAnimalService;
    private IUserService userService;
    private String baseUrl;
    private FavoriteAnimalRestController favoriteAnimalRestController;

    @BeforeEach
    public void setUp() {
        favoriteAnimalService = mock(IFavoriteAnimalService.class);
        userService = mock(IUserService.class);
        baseUrl = "http://localhost:8080/";
        favoriteAnimalRestController = new FavoriteAnimalRestController(favoriteAnimalService, userService, baseUrl);
    }

    @AfterEach
    public void tearDown() {
        favoriteAnimalService = null;
        userService = null;
        baseUrl = null;
        favoriteAnimalRestController = null;
    }

    @Test
    public void getAllAnimalsByUser_ExistingUser_ReturnsListOfAnimals() throws EntityNotFoundException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("elsol");
        user.setEmail("test@example.com");

        List<Animal> animals = new ArrayList<>();
        Animal animal1 = new Animal();
        animal1.setId(1L);
        animal1.setName("Booloobina");
        animal1.setImage("Booloobina.jpg");
        animals.add(animal1);

        when(userService.getUserById(userId)).thenReturn(user);
        when(favoriteAnimalService.getAllAnimalsByUser(user)).thenReturn(animals);

        ResponseEntity<List<Animal>> response = favoriteAnimalRestController.getAllAnimalsByUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Animal> returnedAnimals = response.getBody();
        assertEquals(1, returnedAnimals.size());

        Animal returnedAnimal = returnedAnimals.get(0);
        assertEquals(animal1.getId(), returnedAnimal.getId());
        assertEquals(animal1.getName(), returnedAnimal.getName());
        assertEquals(baseUrl + animal1.getImage(), baseUrl +returnedAnimal.getImage());
        verify(userService).getUserById(userId);
        verify(favoriteAnimalService).getAllAnimalsByUser(user);
        verifyNoMoreInteractions(userService, favoriteAnimalService);
    }

    @Test
    public void getAllAnimalsByUser_NonexistentUser_ReturnsNotFoundResponse() throws EntityNotFoundException {
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(null);

        ResponseEntity<List<Animal>> response = favoriteAnimalRestController.getAllAnimalsByUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService).getUserById(userId);
        verifyNoMoreInteractions(userService, favoriteAnimalService);
    }


}
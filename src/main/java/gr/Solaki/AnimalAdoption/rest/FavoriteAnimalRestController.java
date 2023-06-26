package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.dto.FavoriteAnimalDTO;
import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.service.IFavoriteAnimalService;
import gr.Solaki.AnimalAdoption.service.IUserService;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityAlreadyExists;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-animals")
public class FavoriteAnimalRestController {
    private final IFavoriteAnimalService favoriteAnimalService;
    private final IUserService userService;
    private final String baseUrl;

    @Autowired
    public FavoriteAnimalRestController(IFavoriteAnimalService favoriteAnimalService, IUserService userService, @Value("${animal.image.base-url}") String baseUrl){
        this.favoriteAnimalService=favoriteAnimalService;
        this.userService = userService;
        this.baseUrl = baseUrl;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Animal>> getAllAnimalsByUser(@PathVariable Long userId) throws EntityNotFoundException {

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Animal> animals = favoriteAnimalService.getAllAnimalsByUser(user);

        animals.forEach(animal -> animal.setImage(baseUrl + animal.getImage()));

        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<FavoriteAnimalDTO> saveFavoriteAnimal(
            @RequestParam Long userId,
            @RequestParam Long animalId
    ) throws EntityNotFoundException, EntityAlreadyExists {

        try{
            FavoriteAnimalDTO favoriteAnimalDTO = favoriteAnimalService.saveToFavorites(userId, animalId);
            return ResponseEntity.ok(favoriteAnimalDTO);
        }catch (EntityAlreadyExists e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


    @DeleteMapping("/{userId}/animals/{animalId}")
    public void removeFavoriteAnimal(
            @PathVariable("userId") Long userId,
            @PathVariable("animalId") Long animalId) {

            favoriteAnimalService.removeFavoriteAnimal(userId, animalId);
    }
}
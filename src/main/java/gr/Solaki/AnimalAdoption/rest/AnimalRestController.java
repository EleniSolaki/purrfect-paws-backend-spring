package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.dto.AnimalDTO;
import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.service.IAnimalService;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/animal")
public class AnimalRestController {

    private final IAnimalService animalService;
    private final String baseUrl;

    @Autowired
    public AnimalRestController(IAnimalService animalService, @Value("${animal.image.base-url}") String baseUrl){
        this.animalService=animalService;
        this.baseUrl = baseUrl;
    }

    @RequestMapping(path ="/animals", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalDTO>> getAllAnimals(){
        List<Animal> animals;
        try{
            animals = animalService.getAllAnimals();
            List<AnimalDTO> animalDTO = new ArrayList<>();
            for( Animal animal :animals){
                animalDTO.add(new AnimalDTO(animal.getId(), animal.getAge(), animal.getBreed(), animal.getName(), animal.getGender(), baseUrl + animal.getImage(), animal.getDescription()));
            }
            return new ResponseEntity<>(animalDTO, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/animalbreed/{breed}", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByBreed(@PathVariable("breed") String breed) {
        try {
            List<Animal> animals = animalService.getAnimalsByBreed(breed);
            List<AnimalDTO> dto = new ArrayList<>();
            for (Animal animal : animals) {
                dto.add(new AnimalDTO(animal.getId(), animal.getAge(), animal.getBreed(), animal.getName(), animal.getGender(), baseUrl + animal.getImage(), animal.getDescription() ));
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/animalage/{age}", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByAge(@PathVariable("age") String age) {
        try {
            List<Animal> animals = animalService.getAnimalsByAge(age);
            List<AnimalDTO> animaldto = new ArrayList<>();
            for (Animal animal : animals) {
                animaldto.add(new AnimalDTO(animal.getId(), animal.getAge(), animal.getBreed(), animal.getName(), animal.getGender(), baseUrl + animal.getImage(), animal.getDescription() ));
            }
            return new ResponseEntity<>(animaldto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/animalsgender/{gender}", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByGender(@PathVariable("gender") String gender) {
        try {
            List<Animal> animals = animalService.getAnimalsByGender(gender);
            List<AnimalDTO> dto = new ArrayList<>();
            for (Animal animal : animals) {
                dto.add(new AnimalDTO(animal.getId(), animal.getAge(), animal.getBreed(), animal.getName(), animal.getGender(), baseUrl + animal.getImage(), animal.getDescription()));
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getAnimalNameById(@PathVariable Long id) {
        try {
            String animalName = animalService.getAnimalNameById(id);
            Map<String, String> response = new HashMap<>();
            response.put("name", animalName);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Animal not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}

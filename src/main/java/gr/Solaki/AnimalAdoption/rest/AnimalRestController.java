package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.dto.AnimalDTO;
import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.service.IAnimalService;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all animals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all animals",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnimalDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Bad Request"),
    })
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


    @Operation(summary = "Get all animals by breed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved animals by breed",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnimalDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Bad Request"),
    })
    @RequestMapping(path = "/animalbreed/{breed}", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByBreed(@Parameter(description = "Acceptable breed values: Bombay, European shorthair, Halfbreed Calico, Aegean, Swedish countryside") @PathVariable("breed") String breed) {
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


    @Operation(summary = "Get all animals by age")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved animals by age",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnimalDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(path = "/animalage/{age}", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByAge(@Parameter(description = "Acceptable age values: 1 years old, 2 years old, 3 years old, 7 years old, 13 years old") @PathVariable("age") String age) {
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


    @Operation(summary = "Get all animals by gender")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved animals by gender",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnimalDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(path = "/animalsgender/{gender}", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByGender(@Parameter(description = "Acceptable gender values: Male, Female")@PathVariable("gender") String gender) {
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


    @Operation(summary = "Get animal name by animal_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved animal name",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class, example = "{ \"name\": \"Animal Name\" }")) }),
            @ApiResponse(responseCode = "404", description = "Animal not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class, example = "{ \"error\": \"Animal not found\" }")) })
    })
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

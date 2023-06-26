package gr.Solaki.AnimalAdoption.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String name;
    private String breed;
    private String age;
    private String gender;
    private String image;
    private String description;

    public AnimalDTO(Long id, String age, String breed, String name, String gender, String image, String description) {
        this.id = id;
        this.age = age;
        this.breed = breed;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.description = description;
    }

}

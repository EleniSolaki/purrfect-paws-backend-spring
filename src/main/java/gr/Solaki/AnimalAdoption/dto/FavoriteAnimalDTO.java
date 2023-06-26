package gr.Solaki.AnimalAdoption.dto;

import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteAnimalDTO {
    private Long id;
    private User user;
    private Animal animal;

    public FavoriteAnimalDTO(Long id, User user, Animal animal) {
        this.id = id;
        this.user = user;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}

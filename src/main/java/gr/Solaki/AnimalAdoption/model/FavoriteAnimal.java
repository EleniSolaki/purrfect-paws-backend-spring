package gr.Solaki.AnimalAdoption.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name="FAVORITEANIMALS")
public class FavoriteAnimal {

    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public FavoriteAnimal(Long id, User user, Animal animal) {
        this.id = id;
        this.user = user;
        this.animal = animal;
    }
}

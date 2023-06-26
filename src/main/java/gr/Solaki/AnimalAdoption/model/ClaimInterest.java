package gr.Solaki.AnimalAdoption.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CLAIM_INTEREST")
public class ClaimInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ANIMA_ID")
    private Animal animal;

    @Column(name="COMMENTS", length = 900)
    private String comments;

    @Column(name="OTHER_PETS")
    private boolean otherPetsAtHome;

    public ClaimInterest(Long id, User user, Animal animal, String comments, boolean otherPetsAtHome) {
        this.id = id;
        this.user = user;
        this.animal = animal;
        this.comments = comments;
        this.otherPetsAtHome = otherPetsAtHome;
    }
}

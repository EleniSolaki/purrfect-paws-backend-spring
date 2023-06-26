package gr.Solaki.AnimalAdoption.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="ANIMALS")
public class Animal {

    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="BREED")
    private String breed;

    @Column(name="AGE")
    private String age;

    @Column(name="GENDER")
    private String gender;

    @Column(name="IMAGE")
    private String image;

    @Column(name="DESCRIPTION")
    private String description;


    public Animal(Long id, String name, String breed, String age, String gender, String image, String description) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.image = image;
        this.description = description;
    }
}

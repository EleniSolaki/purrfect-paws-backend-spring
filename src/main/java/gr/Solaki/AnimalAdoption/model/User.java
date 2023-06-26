package gr.Solaki.AnimalAdoption.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name="USERS")
@NoArgsConstructor
public class User {

    @Id
    @Column(name= "ID")
    @GeneratedValue
    private Long id;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;

    @Column(name="USERNAME")
    private String username;

    @Column(name="EMAIL", unique = true)
    private String email;


    @Column(name="PASSWORD")
    private String password;


    public User(Long id, String firstname, String lastname, String username, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

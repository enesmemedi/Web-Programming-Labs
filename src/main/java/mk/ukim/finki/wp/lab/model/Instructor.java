package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String expertise;

    public Instructor(String name, String surname, String email, String expertise) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.expertise = expertise;
    }
}

package az.pashabank.ls.msstudent.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    private String firstName;
    private String lastName;

    public Long collegeId;
}

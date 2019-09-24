package az.pashabank.ls.msstudent.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollegeDto {
    Long id;
    String name;
    String city;
}

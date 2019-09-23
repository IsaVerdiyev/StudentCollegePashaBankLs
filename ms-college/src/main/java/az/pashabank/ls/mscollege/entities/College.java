package az.pashabank.ls.mscollege.entities;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class College {

    private Long id;
    private String name;
    private String city;
}

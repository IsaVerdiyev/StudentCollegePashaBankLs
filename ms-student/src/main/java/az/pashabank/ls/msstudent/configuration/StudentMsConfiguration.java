package az.pashabank.ls.msstudent.configuration;

import az.pashabank.ls.msstudent.models.StudentDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class StudentMsConfiguration {
    @Bean
    HashMap<Long, StudentDto> hashMap(){
        return new HashMap<>(){
            {put(1L, StudentDto.builder().id(1L).firstName("Elvin").lastName("Mahmudov").collegeId(1L).build());
                put(2L, StudentDto.builder().id(2L).firstName("Ayxan").lastName("Agazade").collegeId(2L).build());
                put(3L, StudentDto.builder().id(3L).firstName("Zaur").lastName("Aliyev").collegeId(2L).build());}
        };
    }
}

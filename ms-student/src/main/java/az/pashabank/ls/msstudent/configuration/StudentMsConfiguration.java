package az.pashabank.ls.msstudent.configuration;

import az.pashabank.ls.msstudent.entities.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class StudentMsConfiguration {
    @Bean
    HashMap<Long, Student> hashMap(){
        return new HashMap<>(){
            {put(1L, Student.builder().id(1L).firstName("Elvin").lastName("Mahmudov").collegeId(1L).build());
                put(2L, Student.builder().id(2L).firstName("Ayxan").lastName("Agazade").collegeId(2L).build());
                put(3L, Student.builder().id(3L).firstName("Zaur").lastName("Aliyev").collegeId(2L).build());}
        };
    }
}

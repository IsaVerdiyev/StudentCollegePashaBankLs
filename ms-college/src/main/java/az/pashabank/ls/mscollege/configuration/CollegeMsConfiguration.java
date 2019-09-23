package az.pashabank.ls.mscollege.configuration;

import az.pashabank.ls.mscollege.entities.College;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CollegeMsConfiguration {

    @Bean
    HashMap<Long, College> hashMap(){
        return new HashMap<>(){
            {
                put(1L, College.builder().id(1L).name("ADNSU").city("Baku").build());
                put(2L, College.builder().id(2L).name("MGU").city("Moscow").build());
            }
        };
    }
}

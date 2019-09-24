package az.pashabank.ls.msstudent.services;

import az.pashabank.ls.msstudent.models.CollegeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value ="colleges", url = "localhost:8081")
public interface CollegeClient {
    @GetMapping("/colleges")
    List<CollegeDto> getColleges();
}

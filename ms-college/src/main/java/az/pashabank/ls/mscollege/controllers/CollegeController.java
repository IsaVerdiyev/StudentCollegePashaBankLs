package az.pashabank.ls.mscollege.controllers;


import az.pashabank.ls.mscollege.entities.College;
import az.pashabank.ls.mscollege.interfaces.CollegeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/colleges")
public class CollegeController {
    private CollegeService collegeService;

    @GetMapping
    public List<College> get(){
        return collegeService.recieveAllColleges();
    }

    @GetMapping("/{id}")
    public College get(@PathVariable("id") Long id){
        College college = collegeService.recieveCollegeById(id);
        return college;
    }

    @PostMapping
    public College post(@RequestBody College college){
        return collegeService.addCollege(college);
    }

    @PutMapping
    public College put(@RequestBody College college){
        return collegeService.updateCollege(college);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        collegeService.deleteCollegeById(id);
    }

}

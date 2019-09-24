package az.pashabank.ls.msstudent.controllers;

import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import az.pashabank.ls.msstudent.models.StudentDto;
import az.pashabank.ls.msstudent.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    private StudentService studentService;

    @GetMapping
    public List<StudentDto> get(){
        return studentService.recieveAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto get(@PathVariable("id") Long id){
        logger.info("StudentController.get(Long id) was called with id = " + id);
        StudentDto studentDto = studentService.recieveStudentById(id);
        return studentDto;
    }

    @PostMapping
    public StudentDto post(@RequestBody StudentDto studentDto){
        logger.info("StudentController.post(StudentDto s) was called with s.id = " + studentDto.getId());
        return studentService.addStudent(studentDto);
    }

    @PutMapping
    public StudentDto put(@RequestBody StudentDto studentDto){
        logger.info("StudentController.put(StudentDto s) was called with s.id = " + studentDto.getId());
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        logger.info("StudentController.delete(Long id) was called with id = " + id);
        studentService.deleteStudentById(id);
    }

    @GetMapping(params = {"city"})
    public List<StudentDto> getStudentsByCity(@RequestParam String city){
        logger.info("StudentController.getStudentsByCity(String city) was called with city = " + city);
        return studentService.recieveStudentsByLocation(city);
    }
}

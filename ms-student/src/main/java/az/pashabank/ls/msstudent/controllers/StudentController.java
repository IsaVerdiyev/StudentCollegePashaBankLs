package az.pashabank.ls.msstudent.controllers;

import az.pashabank.ls.msstudent.models.StudentDto;
import az.pashabank.ls.msstudent.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @GetMapping
    public List<StudentDto> get(){
        return studentService.recieveAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto get(@PathVariable("id") Long id){
        StudentDto studentDto = studentService.recieveStudentById(id);
        return studentDto;
    }

    @PostMapping
    public StudentDto post(@RequestBody StudentDto studentDto){
        return studentService.addStudent(studentDto);
    }

    @PutMapping
    public StudentDto put(@RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
    }

    @GetMapping(params = {"city"})
    public List<StudentDto> getStudentsByCity(@RequestParam String city){
        return studentService.recieveStudentsByLocation(city);
    }
}

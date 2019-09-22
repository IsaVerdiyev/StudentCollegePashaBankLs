package az.pashabank.ls.msstudent.controllers;

import az.pashabank.ls.msstudent.entities.Student;
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
    public List<Student> get(){
        return studentService.recieveAllStudents();
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable("id") Long id){
        Student student = studentService.recieveStudentById(id);
        return student;
    }

    @PostMapping
    public Student post(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping
    public Student put(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
    }

}

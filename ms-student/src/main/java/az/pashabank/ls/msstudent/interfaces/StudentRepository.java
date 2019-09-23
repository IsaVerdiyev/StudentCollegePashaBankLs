package az.pashabank.ls.msstudent.interfaces;

import az.pashabank.ls.msstudent.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findById(Long studentId);
    List<Student> findAll();
    Student save(Student student);
    void deleteById(Long studentId);

}

package az.pashabank.ls.msstudent.interfaces;

import az.pashabank.ls.msstudent.models.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Optional<StudentDto> findById(Long studentId);
    List<StudentDto> findAll();
    StudentDto save(StudentDto studentDto);
    void deleteById(Long studentId);

}

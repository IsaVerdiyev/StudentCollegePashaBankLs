package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class StudentHashMapRepository implements StudentRepository {


    HashMap<Long, Student> students;

    @Override
    public Optional<Student> findById(Long studentId) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public void deleteById(Long studentId) {

    }
}

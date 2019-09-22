package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentHashMapRepository implements StudentRepository {
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

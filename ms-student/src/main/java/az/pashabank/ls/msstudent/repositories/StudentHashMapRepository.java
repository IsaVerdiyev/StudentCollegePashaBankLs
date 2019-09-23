package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Repository
@Primary
public class StudentHashMapRepository implements StudentRepository {


    @Setter
    Long id;

    @Autowired
    HashMap<Long, Student> students;

    public StudentHashMapRepository(HashMap<Long, Student> students){

        this.students = students;
        id = (long)students.size();
    }

    @Override
    public Optional<Student> findById(Long studentId) {

        Student student = students.get(studentId);
        if(student == null){
            throw new EntityNotFoundException();
        }
        return Optional.of(students.get(studentId));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student save(Student student) {
        Long studentId = student.getId();
        if(studentId != null && (studentId >= id || !students.containsKey(studentId))) {
            throw new NoSuchElementException();
        }else if(studentId == null) {
            student.setId(++id);

        }
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public void deleteById(Long studentId) {
        students.remove(studentId);
    }
}

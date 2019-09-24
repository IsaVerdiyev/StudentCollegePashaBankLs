package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.models.StudentDto;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Repository
@Primary
public class StudentHashMapRepository implements StudentRepository {


    @Setter
    Long id;

    @Autowired
    HashMap<Long, StudentDto> students;

    public StudentHashMapRepository(HashMap<Long, StudentDto> students){

        this.students = students;
        id = (long)students.size();
    }

    @Override
    public Optional<StudentDto> findById(Long studentId) {

        StudentDto studentDto = students.get(studentId);
        if(studentDto == null){
            throw new EntityNotFoundException();
        }
        return Optional.of(students.get(studentId));
    }

    @Override
    public List<StudentDto> findAll() {
        return new ArrayList<StudentDto>(students.values());
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        Long studentId = studentDto.getId();
        if(studentId != null && (studentId >= id || !students.containsKey(studentId))) {
            throw new NoSuchElementException();
        }else if(studentId == null) {
            studentDto.setId(++id);

        }
        students.put(studentDto.getId(), studentDto);
        return studentDto;
    }

    @Override
    public void deleteById(Long studentId) {
        students.remove(studentId);
    }
}

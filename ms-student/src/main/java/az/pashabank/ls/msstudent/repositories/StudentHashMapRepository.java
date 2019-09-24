package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.models.StudentDto;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Repository
@Primary
public class StudentHashMapRepository implements StudentRepository {

    private static final Logger logger = LoggerFactory.getLogger(StudentHashMapRepository.class);

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
        logger.info("StudentHashMapRepository.findById(Long id) was called with id = " + studentId);
        StudentDto studentDto = students.get(studentId);
        if(studentDto == null){
            logger.warn("Student with id = " + studentId + " was not found");
            throw new EntityNotFoundException();
        }
        return Optional.of(students.get(studentId));
    }

    @Override
    public List<StudentDto> findAll() {
        logger.info("StudentHashMapRepository.findAll() was called");
        return new ArrayList<StudentDto>(students.values());
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        logger.info("StudentHashMapRepository.save(StudentDto s) was called with s.id = " + studentDto.getId());
        Long studentId = studentDto.getId();
        if(studentId != null && (studentId >= id || !students.containsKey(studentId))) {
            logger.warn("Was not found student with id = " + studentDto.getId() + " to update");
            throw new NoSuchElementException();
        }else if(studentId == null) {
            studentDto.setId(++id);

        }
        students.put(studentDto.getId(), studentDto);
        return studentDto;
    }

    @Override
    public void deleteById(Long studentId){
        logger.info("StudentHashMapRepository.deleteById(Long id) was called with id = " + studentId);
        students.remove(studentId);
    }
}

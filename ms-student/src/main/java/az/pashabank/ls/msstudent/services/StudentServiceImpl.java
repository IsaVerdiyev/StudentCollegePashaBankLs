package az.pashabank.ls.msstudent.services;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import az.pashabank.ls.msstudent.interfaces.StudentService;
import az.pashabank.ls.msstudent.repositories.StudentH2Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> recieveAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student recieveStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return student;
    }

    @Override
    public Student addStudent(Student student) {
        try {
            Student foundStudent = recieveStudentById(student.getId());
            logger.warn("Entity already exists");
            throw new EntityExistsException(foundStudent.getId().toString());
        } catch (EntityNotFoundException | InvalidDataAccessApiUsageException | JpaObjectRetrievalFailureException ex) {
            logger.warn(ex.getClass() + ":  " + ex.getMessage());
            Student addedStudent;
            try {
                return studentRepository.save(student);
            }catch(Exception ex1){
                logger.warn(ex1.getClass().getName() + ": " + ex1.getMessage());
                throw ex1;
            }

        }


    }

    @Override
    public Student updateStudent(Student student) {
        Student foundStudent = recieveStudentById(student.getId());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}

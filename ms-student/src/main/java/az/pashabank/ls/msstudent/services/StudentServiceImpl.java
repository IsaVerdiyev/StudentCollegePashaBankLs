package az.pashabank.ls.msstudent.services;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentService;
import az.pashabank.ls.msstudent.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

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
            throw new EntityExistsException(foundStudent.getId().toString());
        } catch (EntityNotFoundException | InvalidDataAccessApiUsageException ex) {
            System.out.println(ex.getClass().toString());
            return studentRepository.save(student);
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

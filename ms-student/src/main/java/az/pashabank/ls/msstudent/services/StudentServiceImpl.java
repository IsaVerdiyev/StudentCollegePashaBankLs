package az.pashabank.ls.msstudent.services;

import az.pashabank.ls.msstudent.models.CollegeDto;
import az.pashabank.ls.msstudent.models.StudentDto;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import az.pashabank.ls.msstudent.interfaces.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CollegeClient collegeClient;

    public StudentServiceImpl(StudentRepository studentRepository, CollegeClient collegeClient){
        this.studentRepository = studentRepository;
        this.collegeClient = collegeClient;
    }

    @Override
    public List<StudentDto> recieveAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDto recieveStudentById(Long studentId) {
        StudentDto studentDto = studentRepository.findById(studentId).get();
        return studentDto;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        try {
            StudentDto foundStudentDto = recieveStudentById(studentDto.getId());
            logger.warn("Entity already exists");
            throw new EntityExistsException(foundStudentDto.getId().toString());
        } catch (EntityNotFoundException | InvalidDataAccessApiUsageException | JpaObjectRetrievalFailureException ex) {
            logger.warn(ex.getClass() + ":  " + ex.getMessage());
            StudentDto addedStudentDto;
            try {
                return studentRepository.save(studentDto);
            }catch(Exception ex1){
                logger.warn(ex1.getClass().getName() + ": " + ex1.getMessage());
                throw ex1;
            }
        }
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        StudentDto foundStudentDto = recieveStudentById(studentDto.getId());
        return studentRepository.save(studentDto);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> recieveStudentsByLocation(String city) {
        List<CollegeDto> colleges = collegeClient.getColleges();
        List<Long> foundCollegeCityIds = colleges.stream()
                .filter(c -> c.getCity().equals(city))
                .map(c -> c.getId())
                .collect(Collectors.toList());
        List<StudentDto> students  = recieveAllStudents();
        return students.stream()
                .filter(s -> foundCollegeCityIds.contains(s.getCollegeId()))
                .collect(Collectors.toList());
    }
}

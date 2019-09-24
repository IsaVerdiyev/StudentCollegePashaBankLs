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

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

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
        logger.info("StudentServiceImpl.recieveAllStudents() was called");
        return studentRepository.findAll();
    }

    @Override
    public StudentDto recieveStudentById(Long studentId) {
        logger.info("StudentServiceImpl.recieveStudentById(Long id) was called with id = " + studentId);
        StudentDto studentDto = studentRepository.findById(studentId).get();
        return studentDto;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        logger.info("StudentServiceImpl.addStudent(StudentDto s) was called with s.id = " + studentDto.getId());
        try {
            StudentDto foundStudentDto = recieveStudentById(studentDto.getId());
            logger.warn("Entity already exists");
            throw new EntityExistsException(foundStudentDto.getId().toString());
        } catch (EntityNotFoundException | InvalidDataAccessApiUsageException | JpaObjectRetrievalFailureException ex) {
            StudentDto addedStudentDto;
            try {
                return studentRepository.save(studentDto);
            }catch(Exception ex1){
                logger.error(ex1.getClass().getName() + ": " + ex1.getMessage());
                throw ex1;
            }
        }
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        logger.info("StudentServiceImpl.updateStudent(StudentDto s) was called with s.id = " + studentDto.getId());
        StudentDto foundStudentDto = recieveStudentById(studentDto.getId());
        return studentRepository.save(studentDto);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        logger.info("StudentServiceImpl.deleteStudentById(Long id) was called with id = " + studentId);
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> recieveStudentsByLocation(String city) {
        logger.info("StudentServiceImpl.recieveStudentsByLocation(String city) was called with city = " + city);
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

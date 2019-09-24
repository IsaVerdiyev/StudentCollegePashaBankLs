package az.pashabank.ls.msstudent.interfaces;

import az.pashabank.ls.msstudent.models.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> recieveAllStudents();

    StudentDto recieveStudentById(Long studentId);

    StudentDto addStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

    void deleteStudentById(Long studentId);

    List<StudentDto> recieveStudentsByLocation(String city);
}

package az.pashabank.ls.msstudent.interfaces;

import az.pashabank.ls.msstudent.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> recieveAllStudents();

    Student recieveStudentById(Long studentId);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudentById(Long studentId);
}

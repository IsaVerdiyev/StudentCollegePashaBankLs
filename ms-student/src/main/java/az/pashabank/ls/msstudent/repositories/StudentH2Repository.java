package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;



public interface StudentH2Repository extends JpaRepository<Student, Long>, StudentRepository {

    @Query("select s from Student s")
    Stream<Student> getStream();

}

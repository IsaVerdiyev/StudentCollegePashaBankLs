package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.models.StudentDto;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;



public interface StudentH2Repository extends JpaRepository<StudentDto, Long>, StudentRepository {

    @Query("select s from StudentDto s")
    Stream<StudentDto> getStream();

}

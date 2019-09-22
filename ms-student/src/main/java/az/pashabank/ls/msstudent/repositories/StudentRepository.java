package az.pashabank.ls.msstudent.repositories;

import az.pashabank.ls.msstudent.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s")
    Stream<Student> getStream();
}

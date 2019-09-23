package az.pashabank.ls.mscollege.interfaces;

import az.pashabank.ls.mscollege.entities.College;

import java.util.List;
import java.util.Optional;

public interface CollegeRepository {
    Optional<College> findById(Long collegeId);
    List<College> findAll();
    College save(College college);
    void deleteById(Long collegeId);
}

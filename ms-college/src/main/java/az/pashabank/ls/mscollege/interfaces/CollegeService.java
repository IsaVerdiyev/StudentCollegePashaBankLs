package az.pashabank.ls.mscollege.interfaces;

import az.pashabank.ls.mscollege.entities.College;

import java.util.List;

public interface CollegeService {
    List<College> recieveAllColleges();

    College recieveCollegeById(Long collegeId);

    College addCollege(College college);

    College updateCollege(College college);

    void deleteCollegeById(Long collegeId);
}

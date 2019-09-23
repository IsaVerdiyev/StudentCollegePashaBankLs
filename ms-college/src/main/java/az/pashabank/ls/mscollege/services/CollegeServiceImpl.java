package az.pashabank.ls.mscollege.services;

import az.pashabank.ls.mscollege.entities.College;
import az.pashabank.ls.mscollege.interfaces.CollegeRepository;
import az.pashabank.ls.mscollege.interfaces.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public List<College> recieveAllColleges() {
        return collegeRepository.findAll();
    }

    @Override
    public College recieveCollegeById(Long collegeId) {
        return collegeRepository.findById(collegeId).get();
    }

    @Override
    public College addCollege(College college) {
        try {
            College foundCollege = recieveCollegeById(college.getId());
            logger.warn("Entity already exists");
            throw new EntityExistsException(foundCollege.getId().toString());
        } catch (EntityNotFoundException | InvalidDataAccessApiUsageException | JpaObjectRetrievalFailureException ex) {
            logger.warn(ex.getClass() + ":  " + ex.getMessage());
            College addedCollege;
            try {
                return collegeRepository.save(college);
            }catch(Exception ex1){
                logger.warn(ex1.getClass().getName() + ": " + ex1.getMessage());
                throw ex1;
            }

        }
    }

    @Override
    public College updateCollege(College college) {
        College foundCollege = recieveCollegeById(college.getId());
        return collegeRepository.save(college);
    }

    @Override
    public void deleteCollegeById(Long collegeId) {
        collegeRepository.deleteById(collegeId);
    }
}

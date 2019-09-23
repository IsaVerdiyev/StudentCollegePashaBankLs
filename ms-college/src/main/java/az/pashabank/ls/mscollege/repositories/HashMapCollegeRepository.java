package az.pashabank.ls.mscollege.repositories;

import az.pashabank.ls.mscollege.entities.College;
import az.pashabank.ls.mscollege.interfaces.CollegeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class HashMapCollegeRepository implements CollegeRepository {

    @Setter
    Long id;

    @Autowired
    HashMap<Long, College> colleges;

    public HashMapCollegeRepository(HashMap<Long, College> colleges){
        this.colleges = colleges;
        id = (long)colleges.size();
    }

    @Override
    public Optional<College> findById(Long collegeId) {
        College college = colleges.get(collegeId);
        if(college == null){
            throw new EntityNotFoundException();
        }
        return Optional.of(college);
    }

    @Override
    public List<College> findAll() {
        return new ArrayList<College>(colleges.values());
    }

    @Override
    public College save(College college) {
        Long collegeId = college.getId();
        if(collegeId != null && (collegeId >= id || !colleges.containsKey(collegeId))) {
            throw new NoSuchElementException();
        }else if(collegeId == null) {
            college.setId(++id);

        }
        colleges.put(college.getId(), college);
        return college;
    }

    @Override
    public void deleteById(Long collegeId) {
colleges.remove(collegeId);
    }
}

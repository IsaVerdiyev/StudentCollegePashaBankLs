package az.pashabank.ls.msstudent;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import az.pashabank.ls.msstudent.repositories.StudentHashMapRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentHashMapRepositoryTests {

	HashMap<Long, Student> hashMap = new HashMap<>(){
		{put(1L, Student.builder().id(1L).firstName("Elvin").lastName("Mahmudov").collegeId(1L).build());
			put(2L, Student.builder().id(2L).firstName("Ayxan").lastName("Agazade").collegeId(2L).build());
			put(3L, Student.builder().id(3L).firstName("Zaur").lastName("Aliyev").collegeId(2L).build());}
	};


	@Test
	public void findByIdTest() {
        StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
        Student student = studentHashMapRepository.findById(1L).get();
        assertEquals("Elvin", student.getFirstName());
        assertEquals("Mahmudov", student.getLastName());
        assertEquals(1, (int) ((long) student.getCollegeId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFoundExceptionTest(){
        StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
        studentHashMapRepository.findById(4L);
    }

	@Test
	public void findAllTest(){
		StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
		List<Student> students = studentHashMapRepository.findAll();
		assertEquals(3, students.size());
	}


	@Test
	public void saveTest(){
		HashMap<Long, Student> mockHashMap = new HashMap<>(hashMap);
		Student addedStudent = Student.builder().firstName("Ulvi").lastName("Xalilov").collegeId(3L).build();
		StudentHashMapRepository studentHashMapRepository = getMockRepository(mockHashMap);
		studentHashMapRepository.save(addedStudent);

		assertEquals(4L, mockHashMap.size());
		assertTrue(mockHashMap.containsKey(4L));
		assertTrue(mockHashMap.containsValue(addedStudent));
	}

	@Test(expected = NoSuchElementException.class)
    public void saveExceptionTest1(){
        StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
        studentHashMapRepository.save(Student.builder().id(4L).firstName("Ulvi").lastName("Xalilov").collegeId(3L).build());
    }

    @Test(expected = NoSuchElementException.class)
    public void saveExceptionTest2(){
	    HashMap<Long, Student> mockHashMap = new HashMap<>(hashMap);
	    mockHashMap.remove(2L);
        StudentHashMapRepository studentHashMapRepository = getMockRepository(mockHashMap);
        studentHashMapRepository.save(Student.builder().id(2L).firstName("Ulvi").lastName("Xalilov").collegeId(3L).build());
    }

	@Test
	public void deleteByIdTest(){
		HashMap<Long, Student> mockHashMap = new HashMap<>(hashMap);
		StudentHashMapRepository studentHashMapRepository = getMockRepository(mockHashMap);
		studentHashMapRepository.deleteById(1L);
		assertEquals(2, mockHashMap.size());
		assertFalse(mockHashMap.containsKey(1L));

	}


    private StudentHashMapRepository getMockRepository(HashMap<Long, Student> mockHashMap){
        StudentHashMapRepository studentHashMapRepository = new StudentHashMapRepository(mockHashMap);
        studentHashMapRepository.setId(3L);
        return studentHashMapRepository;
    }


}

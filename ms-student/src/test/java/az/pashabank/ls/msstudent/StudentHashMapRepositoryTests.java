package az.pashabank.ls.msstudent;

import az.pashabank.ls.msstudent.entities.Student;
import az.pashabank.ls.msstudent.interfaces.StudentRepository;
import az.pashabank.ls.msstudent.repositories.StudentHashMapRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

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
		StudentHashMapRepository studentHashMapRepository = new StudentHashMapRepository(new HashMap<>(hashMap));
		Student student = studentHashMapRepository.findById(1L).get();
		assertEquals("Elvin", student.getFirstName());
		assertEquals("Mahmudov", student.getLastName());
		assertEquals(1,  (int)((long) student.getCollegeId()));

	}

	@Test
	public void findAllTest(){
		StudentHashMapRepository studentHashMapRepository = new StudentHashMapRepository(new HashMap<>(hashMap));
		List<Student> students = studentHashMapRepository.findAll();
		assertEquals(3, students.size());
	}


	@Test
	public void saveTest(){
		HashMap<Long, Student> mockHashMap = new HashMap<>(hashMap);
		Student addedStudent = Student.builder().id(4L).firstName("Ulvi").lastName("Xalilov").collegeId(3L).build();
		StudentHashMapRepository studentHashMapRepository = new StudentHashMapRepository(mockHashMap);
		studentHashMapRepository.save(addedStudent);

		assertEquals(4L, mockHashMap.size());
		assertTrue(mockHashMap.containsKey(4L));
		assertTrue(mockHashMap.containsValue(addedStudent));
	}

	@Test
	public void deleteByIdTest(){
		HashMap<Long, Student> mockHashMap = new HashMap<>(hashMap);
		StudentHashMapRepository studentHashMapRepository = new StudentHashMapRepository(mockHashMap);
		studentHashMapRepository.deleteById(1L);
		assertEquals(2, mockHashMap.size());
		assertFalse(mockHashMap.containsKey(1L));

	}





}

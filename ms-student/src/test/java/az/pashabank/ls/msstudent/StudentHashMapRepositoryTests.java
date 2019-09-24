package az.pashabank.ls.msstudent;

import az.pashabank.ls.msstudent.models.StudentDto;
import az.pashabank.ls.msstudent.repositories.StudentHashMapRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentHashMapRepositoryTests {

	HashMap<Long, StudentDto> hashMap = new HashMap<>(){
		{put(1L, StudentDto.builder().id(1L).firstName("Elvin").lastName("Mahmudov").collegeId(1L).build());
			put(2L, StudentDto.builder().id(2L).firstName("Ayxan").lastName("Agazade").collegeId(2L).build());
			put(3L, StudentDto.builder().id(3L).firstName("Zaur").lastName("Aliyev").collegeId(2L).build());}
	};


	@Test
	public void findByIdTest() {
        StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
        StudentDto studentDto = studentHashMapRepository.findById(1L).get();
        assertEquals("Elvin", studentDto.getFirstName());
        assertEquals("Mahmudov", studentDto.getLastName());
        assertEquals(1, (int) ((long) studentDto.getCollegeId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFoundExceptionTest(){
        StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
        studentHashMapRepository.findById(4L);
    }

	@Test
	public void findAllTest(){
		StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
		List<StudentDto> studentDtos = studentHashMapRepository.findAll();
		assertEquals(3, studentDtos.size());
	}


	@Test
	public void saveTest(){
		HashMap<Long, StudentDto> mockHashMap = new HashMap<>(hashMap);
		StudentDto addedStudentDto = StudentDto.builder().firstName("Ulvi").lastName("Xalilov").collegeId(3L).build();
		StudentHashMapRepository studentHashMapRepository = getMockRepository(mockHashMap);
		studentHashMapRepository.save(addedStudentDto);

		assertEquals(4L, mockHashMap.size());
		assertTrue(mockHashMap.containsKey(4L));
		assertTrue(mockHashMap.containsValue(addedStudentDto));
	}

	@Test(expected = NoSuchElementException.class)
    public void saveExceptionTest1(){
        StudentHashMapRepository studentHashMapRepository = getMockRepository(new HashMap<>(hashMap));
        studentHashMapRepository.save(StudentDto.builder().id(4L).firstName("Ulvi").lastName("Xalilov").collegeId(3L).build());
    }

    @Test(expected = NoSuchElementException.class)
    public void saveExceptionTest2(){
	    HashMap<Long, StudentDto> mockHashMap = new HashMap<>(hashMap);
	    mockHashMap.remove(2L);
        StudentHashMapRepository studentHashMapRepository = getMockRepository(mockHashMap);
        studentHashMapRepository.save(StudentDto.builder().id(2L).firstName("Ulvi").lastName("Xalilov").collegeId(3L).build());
    }

	@Test
	public void deleteByIdTest(){
		HashMap<Long, StudentDto> mockHashMap = new HashMap<>(hashMap);
		StudentHashMapRepository studentHashMapRepository = getMockRepository(mockHashMap);
		studentHashMapRepository.deleteById(1L);
		assertEquals(2, mockHashMap.size());
		assertFalse(mockHashMap.containsKey(1L));

	}


    private StudentHashMapRepository getMockRepository(HashMap<Long, StudentDto> mockHashMap){
        StudentHashMapRepository studentHashMapRepository = new StudentHashMapRepository(mockHashMap);
        studentHashMapRepository.setId(3L);
        return studentHashMapRepository;
    }


}

package io.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.model.ModelApiResponse;
import io.swagger.model.Student;
import io.swagger.repository.StudentRepositoryInterface;


@Service
@Transactional
public class StudentService implements StudentServiceInterface{
	
	@Autowired
    StudentRepositoryInterface studentRepository;
	
	@Autowired
	public StudentService(StudentRepositoryInterface studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public ModelApiResponse addStudent(Student s) {
		// TODO Auto-generated method stub
		
		studentRepository.save(s);
		ModelApiResponse m=new ModelApiResponse();
		m.setCode(200);
		m.setType("SUCCESS");
		m.setMessage("Successfully Saved");
		
		return m;
	}

	@Override
	public ModelApiResponse updateStudent(Student s) throws Exception {
		
		
		Student s1= studentRepository.findById(s.getId()).orElseThrow(()-> new Exception());
		s1.setId(s.getId());
		s1.setName(s.getName());
		s1.setMarks(s.getMarks());
		studentRepository.save(s1);
		ModelApiResponse m=new ModelApiResponse();
		m.setCode(200);
		m.setType("SUCCESS");
		m.setMessage("Successfully Updated");
		
		return m;
	}

	

	@Override
	public Student getStudentById(Integer id) throws Exception {
		
		Optional<Student> op=studentRepository.findById(id);
		Student s=op.orElseThrow(()-> new Exception());
		
		return s;
	}

	@Override
	public ModelApiResponse deleteStudent(Integer id)  throws Exception {
		
		Optional<Student> op=studentRepository.findById(id);
		if(op.isPresent()) {
		studentRepository.deleteById(id);}
		else {
			throw new Exception();
		}
		ModelApiResponse m=new ModelApiResponse();
		m.setCode(200);
		m.setType("SUCCESS");
		m.setMessage("Successfully Deleted");
		
		return m;
	}

	@Override
	public List<Student> getAllStudent() throws Exception {
		// TODO Auto-generated method stub
		List<Student> students =(List<Student>) studentRepository.findAll();
		return students ;
		
	}
	
	@Override
	public List<Student> getStudentMarksGreater(long m) throws Exception {
		List<Student> students = studentRepository.findByMarksGreaterThan(m);
		return students;
		
	}
	
	@Override
	public List<Student> getTop5Students() throws Exception {
		List<Student> students = studentRepository.findTop5ByOrderByMarksDesc();
		return students;
		
	}

	
}

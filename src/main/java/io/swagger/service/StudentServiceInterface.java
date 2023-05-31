package io.swagger.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.swagger.model.ModelApiResponse;
import io.swagger.model.Student;

@Service
public interface StudentServiceInterface {

	
	public ModelApiResponse addStudent(Student s);
	public ModelApiResponse updateStudent(Student s) throws Exception;
	public Student getStudentById(Integer id) throws Exception;
	public ModelApiResponse deleteStudent(Integer id) throws Exception;
	public List<Student> getAllStudent() throws Exception;
	public List<Student> getStudentMarksGreater(long m) throws Exception;
	public List<Student> getTop5Students() throws Exception;
	
	
}

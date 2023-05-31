package io.swagger.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.ModelApiResponse;
import io.swagger.model.Student;
import io.swagger.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import reactor.core.publisher.Flux;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T05:07:20.772145680Z[GMT]")
@RestController
public class StudentApiController implements StudentApi {
//http://localhost:8080/student/v3/getStudentByPage/1
    private static final Logger log = LoggerFactory.getLogger(StudentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public StudentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    @Autowired
    StudentService studentservice;

    @PostMapping("/add")
    public ResponseEntity<ModelApiResponse> addStudent(@Parameter(in = ParameterIn.DEFAULT,required=true, schema=@Schema())
    @Valid @RequestBody Student student) {
            try {
            	ModelApiResponse m=studentservice.addStudent(student);
                return new ResponseEntity<ModelApiResponse>(m, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    @PutMapping("/update/{id}")
    public ResponseEntity<ModelApiResponse> updateStudent(@Parameter(in = ParameterIn.PATH,  required=true, schema=@Schema()) 
    @Valid @RequestBody Student student) {
        try { 
        	    ModelApiResponse m=studentservice.updateStudent(student);
                return new ResponseEntity<ModelApiResponse>
                ( m, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
      
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ModelApiResponse> deleteStudent(@Parameter(in = ParameterIn.PATH,  
    required=true, schema=@Schema()) @PathVariable("id") int id) {
       try {
    	   
    	   ModelApiResponse m=studentservice.deleteStudent(id);
                return new ResponseEntity<ModelApiResponse>(m, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
      }
    
    
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@Parameter(in = ParameterIn.PATH,  required=true, schema=@Schema()) 
    @PathVariable("id") Integer id) throws Exception {
        
            try {
            	Student s= studentservice.getStudentById(id);
            	
                return new ResponseEntity<Student>(s, 
                		HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Student>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent(
    ) throws Exception {
        
            try {
            	List<Student> s= studentservice.getAllStudent();
            	
                return new ResponseEntity<List<Student>>(s, 
                		HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @GetMapping("/getTop5")
    public ResponseEntity<List<Student>> getTop5Students(
    ) throws Exception {
        
            try {
            	List<Student> s= studentservice.getTop5Students();
            	
                return new ResponseEntity<List<Student>>(s, 
                		HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    
	@Override
	@GetMapping("/getMarksGreaterThan/{m}")
	public ResponseEntity<List<Student>> getStudentMarksGreaterThan(@Parameter(in = ParameterIn.PATH,  required=true, schema=@Schema()) 
    @PathVariable("m") long m) throws Exception {
		// TODO Auto-generated method stub
		try {
        	List<Student> s= studentservice.getStudentMarksGreater(m);
        	
            return new ResponseEntity<List<Student>>(s, 
            		HttpStatus.OK);
        } catch (IOException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@Override
	@GetMapping("/client")
	public Student getClient() {
		// TODO Auto-generated method stub
		String uri="http://localhost:8080/student/v3/get/1";
		RestTemplate restTemplate = new RestTemplate();
		
		Student response = restTemplate.getForObject(uri, Student.class);
          
		
		return response;
	}
	
	@Override
	@GetMapping("/Webclient/{id}")
	public Flux<Student> getWebClient(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		String uri="http://localhost:8080/student/v3/get/"+id;
		
		WebClient client = WebClient.create();

		 return client.get()
		    .uri(uri)
		    .retrieve().bodyToFlux(Student.class);
		
	}
	
	
	
	
    }
    
    
    
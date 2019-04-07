package com.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentRepo studentRepo;

	@GetMapping(value = "/student/{id}")
	public ResponseStudent getStudent(@PathVariable(name = "id") int studentId) {
		Student student = studentRepo.findById(studentId).get();
		ResponseStudent response = new ResponseStudent();
		if (student != null) {
			response.setType("Success");
			response.setCode(200);
			response.setStudentData(Arrays.asList(student));
		}
		return response;
	}

	@PostMapping(value = "/addStudent")
	public void addStudent(@RequestBody Student student) {
		studentRepo.save(student);
	}

	@GetMapping(value = "/getAllStudents")
	public ResponseStudent getAllStudents() {
		ResponseStudent response = new ResponseStudent();

		List<Student> studentList = studentRepo.findAll();
		if (!CollectionUtils.isEmpty(studentList)) {
			response.setType("Success");
			response.setCode(200);
			response.setStudentData(studentList);
		}
		return response;
	}
}

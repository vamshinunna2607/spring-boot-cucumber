package com.demo;

import java.io.IOException;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(classes = SpringBootCucumberDemoApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class StudentStepDefs {

	@Autowired
	TestRestTemplate restTemplate;

	ResponseStudent response;

	@Given("the student with id {int} already exsist")
	public void the_student_with_id_already_exsist(int studentId) {
		Student s = new Student();
		s.setId(studentId);
		s.setAge(12);
		s.setDept("CS");
		s.setFirstName("John");
		s.setLastName("Doe");
		restTemplate.postForObject("/addStudent", s, Object.class);
	}

	@When("the client calls GET \\/student\\/{int}")
	public void the_client_calls_GET_student(Integer studentId) {
		String url = "/student/" + studentId;
		response = restTemplate.getForObject(url, ResponseStudent.class);
	}

	@When("the user calls GET \\/getAllStudents")
	public void the_user_calls_GET_getAllStudents() {
		response = restTemplate.getForObject("/getAllStudents", ResponseStudent.class);
	}

	@Then("assert the response is not null")
	public void assert_the_response_is_not_null() {
		Assert.assertNotNull(response);
	}

	@Then("the response status is {int}")
	public void the_response_status_is(int status) {
		Assert.assertEquals(response.getCode(), status);
	}

	@Then("the response contains student object with student id as {int}")
	public void the_response_contains_student_object_with_student_id_as(Integer studentId) {
		Assert.assertNotNull(response.getStudentData());
		Assert.assertEquals(response.getStudentData().get(0).getId(), studentId);
	}

	@Then("the response should should match the JSON object:")
	public void the_response_should_should_match_the_JSON_object(String actualJson) throws IOException {
		ObjectMapper objMapper = new ObjectMapper();

		String expectedJsonStr = objMapper.writeValueAsString(response.getStudentData());
		JsonNode actualJsonObject = objMapper.readTree(actualJson);
		JsonNode expectedJsonObject = objMapper.readTree(expectedJsonStr);
		Assert.assertEquals(expectedJsonObject, actualJsonObject);
	}
}

Feature: the student controller API is being tested

  Scenario: user makes call to GET /student
    Given the student with id 1 already exsist
    When the client calls GET /student/1
    Then assert the response is not null
    And the response status is 200
    And the response contains student object with student id as 1

  Scenario: user makes call to GET /getAllStudents
    When the user calls GET /getAllStudents
    Then assert the response is not null
    And the response status is 200
    And the response should should match the JSON object:
    """
    [
	    {
	        "id": 1,
	        "firstName": "John",
	        "lastName": "Doe",
	        "age": 12,
	        "dept": "CS"
	    }
		]
    """

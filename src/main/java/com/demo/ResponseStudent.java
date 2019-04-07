package com.demo;

import java.util.List;

public class ResponseStudent {

	private String type;
	private int code;
	private List<Student> studentData;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ResponseStudent(String type, int code) {
		super();
		this.type = type;
		this.code = code;
	}

	public ResponseStudent() {
		super();
	}

	public List<Student> getStudentData() {
		return studentData;
	}

	public void setStudentData(List<Student> studentData) {
		this.studentData = studentData;
	}

}

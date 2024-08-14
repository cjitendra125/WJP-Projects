package com.student.model;

public class StudentModel {
	private int id;
	private String name;
	private float marks;
	private int num;
	
	public StudentModel() {}
	
	public StudentModel(int id, String name, float marks, int num) {
		
		this.id = id;
		this.name = name;
		this.marks = marks;
		this.num = num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMarks() {
		return marks;
	}
	public void setMarks(float marks) {
		this.marks = marks;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "StudentModel [id=" + id + ", name=" + name + ", marks=" + marks + ", num=" + num + "]";
	}
	

}

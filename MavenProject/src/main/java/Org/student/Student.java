package Org.student;

import Org.department.Department;

public class Student extends Department {
	
	public void studentName() {
		// TODO Auto-generated method stub
System.out.println("Student Name is Veeravel");
	}
	
	public void studentDept() {
		// TODO Auto-generated method stub
System.out.println("Student Dept is IT");
	}
	
	public void studentId() {
		// TODO Auto-generated method stub
		System.out.println("Student Id is:3347");

	}


public static void main(String[] args) {
	// TODO Auto-generated method stub
Student stud= new Student(); 
stud.collegeCode();
stud.collegeName();
stud.collegeRank();
stud.deptName();
stud.studentDept();
stud.studentId();
stud.studentName();

}
}
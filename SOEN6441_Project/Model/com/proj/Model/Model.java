package com.proj.Model;

public class Model {
	
	 private String firstname;
	 private String lastname;
	 private int age;
	 
	 public Model(String firstname, String lastname,int age) {
	  this.firstname = firstname;
	  this.lastname = lastname;
	  this.age=age;
	 }
	 public String getFirstname() {
	  return firstname;
	 }
	 public void setFirstname(String firstname) {
	  this.firstname = firstname;
	 }
	 public String getLastname() {
	  return lastname;
	 }
	 public void setLastname(String lastname) {
	  this.lastname = lastname;
	 }
	 public int getage()
	 {
		 return age;
	 }
	 public void setAge(int age) {
		 this.age=age;
	 }
	
}

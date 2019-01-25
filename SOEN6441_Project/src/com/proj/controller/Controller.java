package com.proj.controller;
import javax.swing.JOptionPane;

import com.proj.model.Model;
import com.proj.views.View;

public class Controller {
	
	 private Model model;
	 private View view;
	 public Controller(Model m, View v) {
	  model = m;
	  view = v;
	  initView();
	 }
	 public void initView() {
	  view.getFirstnameTextfield().setText(model.getFirstname());
	  view.getLastnameTextfield().setText(model.getLastname());
	  view.getagetextfeild().setText(String.valueOf(model.getage()));
	 }
	 public void initController() {
	  view.getFirstnameSaveButton().addActionListener(e -> saveFirstname());
	  view.getLastnameSaveButton().addActionListener(e -> saveLastname());
	  view.getAgeSaveButton().addActionListener(e -> saveAge());
	  
	  view.getHello().addActionListener(e -> sayHello());
	  view.getBye().addActionListener(e -> sayBye());
	  view.getMy_Age_IsButton().addActionListener(e -> getMy_Age_IsButton());
	  
	 }
	 public void getMy_Age_IsButton() {
		// TODO Auto-generated method stub
		 model.setAge(Integer.parseInt(view.getagetextfeild().getText()));
		 JOptionPane.showMessageDialog(null, "My Age is : " + model.getage(), "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}
	public void saveAge() {
		// TODO Auto-generated method stub
	model.setAge(Integer.parseInt(view.getagetextfeild().getText()));
	 JOptionPane.showMessageDialog(null, "Age saved : " + model.getage(), "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	private void saveFirstname() {
	  model.setFirstname(view.getFirstnameTextfield().getText());
	  JOptionPane.showMessageDialog(null, "Firstname saved : " + model.getFirstname(), "Info", JOptionPane.INFORMATION_MESSAGE);
	 }
	 private void saveLastname() {
	  model.setLastname(view.getLastnameTextfield().getText());
	  JOptionPane.showMessageDialog(null, "Lastname saved : " + model.getLastname(), "Info", JOptionPane.INFORMATION_MESSAGE);
	 }
	 private void sayHello() {
	  JOptionPane.showMessageDialog(null, "Hello " + model.getFirstname() + " " + model.getLastname(), "Info", JOptionPane.INFORMATION_MESSAGE);
	 }
	 private void sayBye() {
	  System.exit(0);
	 }
	

}

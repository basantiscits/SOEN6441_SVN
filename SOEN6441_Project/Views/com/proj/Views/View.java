package com.proj.Views;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View {
	// control to add on screen..

	// View uses Swing framework to display UI to user
	private JFrame frame;
	private JLabel firstnameLabel;
	private JLabel lastnameLabel;
	private JTextField firstnameTextfield;
	private JTextField lastnameTextfield;
	private JButton firstnameSaveButton;
	private JButton lastnameSaveButton;
	private JButton hello;
	private JButton bye;
	private JButton AgeSaveButton;
	private JLabel AgeLabel;
	private JTextField AgeTextFeild;
	private JButton My_Age_Is;

	public View(String title) {
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 240);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Create UI elements
		firstnameLabel = new JLabel("Firstname :");
		lastnameLabel = new JLabel("Lastname :");
		AgeLabel = new JLabel("Age :");

		firstnameTextfield = new JTextField();
		lastnameTextfield = new JTextField();
		AgeTextFeild = new JTextField();

		firstnameSaveButton = new JButton("Save firstname");
		lastnameSaveButton = new JButton("Save lastname");
		AgeSaveButton = new JButton("Save Age");

		hello = new JButton("Hello!");
		bye = new JButton("Bye!");
		My_Age_Is = new JButton("My age is !");

		// Add UI element to frame
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(firstnameLabel).addComponent(lastnameLabel).addComponent(AgeLabel))

						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(firstnameTextfield).addComponent(lastnameTextfield)
								.addComponent(AgeTextFeild))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(firstnameSaveButton).addComponent(lastnameSaveButton)
								.addComponent(AgeSaveButton))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hello)
								.addComponent(bye).addComponent(My_Age_Is)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(firstnameLabel)
						.addComponent(firstnameTextfield).addComponent(firstnameSaveButton).addComponent(hello))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lastnameLabel)
						.addComponent(lastnameTextfield).addComponent(lastnameSaveButton).addComponent(bye))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(AgeLabel)
						.addComponent(AgeTextFeild).addComponent(AgeSaveButton).addComponent(My_Age_Is)));
		layout.linkSize(SwingConstants.HORIZONTAL, firstnameSaveButton, lastnameSaveButton, AgeSaveButton);
		layout.linkSize(SwingConstants.HORIZONTAL, hello, bye, My_Age_Is);
		frame.getContentPane().setLayout(layout);
	}

	// -------------------------------------------------
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	// -------------------------------------------------
	public JLabel getFirstnameLabel() {
		return firstnameLabel;
	}

	public void setFirstnameLabel(JLabel firstnameLabel) {
		this.firstnameLabel = firstnameLabel;
	}

	// -------------------------------------------------
	public JLabel getLastnameLabel() {
		return lastnameLabel;
	}

	public void setLastnameLabel(JLabel lastnameLabel) {
		this.lastnameLabel = lastnameLabel;
	}

	// -------------------------------------------------
	public void setageLabel(JLabel AgeLabel) {
		this.AgeLabel = AgeLabel;
	}

	public JLabel getageLabel() {
		return AgeLabel;
	}
	// -------------------------------------------------

	public JTextField getFirstnameTextfield() {
		return firstnameTextfield;
	}

	public void setFirstnameTextfield(JTextField firstnameTextfield) {
		this.firstnameTextfield = firstnameTextfield;
	}

	// -------------------------------------------------
	public JTextField getLastnameTextfield() {
		return lastnameTextfield;
	}

	public void setLastnameTextfield(JTextField lastnameTextfield) {
		this.lastnameTextfield = lastnameTextfield;
	}

	// -------------------------------------------------
	public void setagetextfeild(JTextField AgeTextFeild) {
		this.AgeTextFeild = AgeTextFeild;
	}

	public JTextField getagetextfeild() {
		return AgeTextFeild;
	}

	// -------------------------------------------------
	public void setFirstnameSaveButton(JButton firstnameSaveButton) {
		this.firstnameSaveButton = firstnameSaveButton;
	}

	public JButton getFirstnameSaveButton() {
		return firstnameSaveButton;
	}

	// -------------------------------------------------
	public void setLastnameSaveButton(JButton firstnameSaveButton) {
		this.firstnameSaveButton = firstnameSaveButton;
	}

	public JButton getLastnameSaveButton() {
		return lastnameSaveButton;
	}
	// -------------------------------------------------

	public void setHello(JButton hello) {
		this.hello = hello;
	}

	public JButton getHello() {
		return hello;
	}
	// -------------------------------------------------

	public void setBye(JButton bye) {
		this.bye = bye;
	}

	public JButton getBye() {
		return bye;
	}
	// -------------------------------------------------

	public void setAgeSaveButton(JButton AgeSaveButton) {
		this.AgeSaveButton = AgeSaveButton;
	}
	
	public JButton getAgeSaveButton() {
		return AgeSaveButton;
	}

	


	public void setMy_Age_IsButton(JButton My_Age_Is) {
		this.My_Age_Is = My_Age_Is;
	}

	public JButton getMy_Age_IsButton() {
		return My_Age_Is;
	}

}

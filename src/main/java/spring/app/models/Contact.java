package spring.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "CONTACTS")
public class Contact {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "NAME")
	@NotEmpty(message = "Name should be not empty")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30")
	private String name;
	
	@Column(name = "PHONE_NUMBER")
	@NotNull(message = "Phone number should be not empty")
	private int phoneNumber;
	
	@Column(name = "EMAIL")
	@NotEmpty(message = "Email should be not empty")
	@Email(message = "Email should be valid")
	private String email;
	
	public Contact() {		
	}
	
	public Contact(int id, String name, int phoneNumber, String email) {
		this.id = id;
		this.name = name;
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

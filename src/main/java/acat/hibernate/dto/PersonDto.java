package acat.hibernate.dto;

import java.io.Serializable;

import acat.hibernate.model.BaseEntity;
import acat.hibernate.model.FullName;
import acat.hibernate.model.Laptop;
import acat.hibernate.model.Person;

public class PersonDto extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915317817868710134L;

	private FullName fullName;
	private String email;
	private String phNo;
	private Laptop laptop;
	
	public PersonDto(Person person) {
		super.setId(person.getId());
		this.fullName = person.getFullName();
		this.email = person.getEmail();
		this.phNo = person.getPhNo();
		this.laptop = person.getLaptop();
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
}

package acat.hibernate.controller;

import acat.hibernate.dao.PersonDao;
import acat.hibernate.model.FullName;
import acat.hibernate.model.Laptop;
import acat.hibernate.model.Person;
import acat.hibernate.view.PersonView;
import acat.hibernate.view.Status;

public class PersonController {
	
	private Person model;
	private PersonView view;

	public PersonController(Person model, PersonView view) {
		this.model = model;
		this.view = view;
	}
	
	public Person getPerson() {
		return model;
	}
	
	public void setPerson(Person model) {
		this.model = model;
	}
	
	public FullName getPersonFullName() {
		return model.getFullName();
	}
	
	public void setPersonFullName(FullName fullName) {
		model.setFullName(fullName);
	}
	
	public String getPersonEmail() {
		return model.getEmail();
	}
	
	public void setPersonEmail(String email) {
		model.setEmail(email);
	}
	
	public String getPhoneNo() {
		return model.getPhNo();
	}
	
	public void setPhoneNo(String phNo) {
		model.setPhNo(phNo);
	}
	
	public Laptop getLaptopOwnedByPerson() {
		return model.getLaptop();
	}
	
	public void setLaptopOwnedByPerson(Laptop laptop) {
		model.setLaptop(laptop);
	}
	
	public void updateView() {
		view.printPersonDetails(model);
	}
	
	public void saveToDatabase(Status status) {
		PersonDao personDao = new PersonDao();
		boolean success = false;
		try {
			personDao.save(model);
			success = true;
		}
		catch (Exception e) {
			success = false;
		}
		if (success) {
			switch (status) {
			case SHOW:
				System.out.println("SAVED SUCCESSFULLY");
				break;
			case HIDE:
				break;
			}
		}
	}
}

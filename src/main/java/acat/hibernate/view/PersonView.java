package acat.hibernate.view;

import acat.hibernate.model.Person;

public class PersonView {

	public void printPersonDetails(Person model) {
		StringBuilder sb = new StringBuilder();
		sb.append("Person [id="+model.getId());
		sb.append(", name="+model.getFullName().getFirstName());
		sb.append(" "+model.getFullName().getLastName());
		sb.append(", email="+model.getEmail());
		sb.append(", phNo="+model.getPhNo());
		sb.append(", fk_laptop_id="+model.getLaptop().getId()+"]");
		System.out.println(sb.toString());
	}

}

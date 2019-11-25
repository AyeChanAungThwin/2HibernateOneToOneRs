package acat.hibernate.view;

import acat.hibernate.model.Person;

public class PersonView extends AbstractViewImpl<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3259377131146158720L;

	public void printDetails(Person model) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("< ");
		sb.append(model.getClass().getSimpleName());
		sb.append(" Output>[id="+model.getId());
		sb.append(", fullName="+model.getFullName().getFirstName()+" "+model.getFullName().getLastName());
		sb.append(", email="+model.getEmail());
		sb.append(", phNo="+model.getPhNo());
		sb.append(", foreignKeyLaptopId="+model.getLaptop().getId()+"]\n*****");
		System.err.println(sb.toString());
	}
}

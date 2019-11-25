package acat.hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import acat.hibernate.dto.PersonDto;
import acat.hibernate.model.Person;
import acat.hibernate.service.PersonService;
import acat.hibernate.service.PersonServiceImpl;
import acat.hibernate.view.PersonView;

public class PersonControllerImpl extends AbstractControllerImpl<Person, PersonView> implements PersonController<Person>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 441459733921267928L;
	
	private final PersonService service = new PersonServiceImpl();
	
	@Override
	public Person getModel() {
		// TODO Auto-generated method stub
		return super.getModel();
	}

	@Override
	public void setModel(Person model) {
		// TODO Auto-generated method stub
		super.setModel(model);
	}

	@Override
	public PersonView getView() {
		// TODO Auto-generated method stub
		return super.getView();
	}

	@Override
	public void setView(PersonView view) {
		// TODO Auto-generated method stub
		super.setView(view);
	}

	@Override
	public void printDetails(Person model) {
		// TODO Auto-generated method stub
		super.printDetails(model);
	}

	@Override
	public void printDetails(List<Person> modelList) {
		// TODO Auto-generated method stub
		super.printDetails(modelList);
	}

	public Person findById(long id) {
		// TODO Auto-generated method stub
		PersonDto dto = service.findOne(id);
		return dto.getEntity();
	}

	public List<Person> findAll() {
		// TODO Auto-generated method stub
		List<PersonDto> dtoList = service.findAll();
		List<Person> people = null;
		for (PersonDto dto: dtoList) {
			if (people==null) {
				people = new ArrayList<Person>();
			}
			people.add(dto.getEntity());
		}
		return people;
	}

	public Person save(Person model) {
		// TODO Auto-generated method stub
		PersonDto dto = service.save(new PersonDto(model));
		return dto.getEntity();
	}

	public Person update(Person model) {
		// TODO Auto-generated method stub
		PersonDto dto = service.update(new PersonDto(model));
		return dto.getEntity();
	}

	public void delete(Person model) {
		// TODO Auto-generated method stub
		service.delete(new PersonDto(model));
	}

	public void deleteById(long id) {
		// TODO Auto-generated method stub
		service.deleteById(id);
	}
}
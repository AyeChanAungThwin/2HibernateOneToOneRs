package acat.hibernate;

import java.util.List;

import acat.hibernate.controller.LaptopController;
import acat.hibernate.controller.LaptopControllerImpl;
import acat.hibernate.controller.PersonController;
import acat.hibernate.controller.PersonControllerImpl;
import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dao.PersonDao;
import acat.hibernate.dto.LaptopDto;
import acat.hibernate.model.FullName;
import acat.hibernate.model.Laptop;
import acat.hibernate.model.Person;

public class App {
	
	public static void main( String[] args ) {
		//easy1();
		//easy2();
		
		//hard1();
		hard2();
    }
	
	public static void easy1() {
		Laptop laptop1 = new Laptop();
		laptop1.setBrand("Lenovo");
		laptop1.setDescription("It is Lenovo laptop.");
		
		Laptop laptop2 = new Laptop();
		laptop2.setBrand("HP");
		laptop2.setDescription("It is HP laptop.");
		
		Person person = new Person();
		person.setFullName(new FullName("Mr", "Dumb"));
		person.setEmail("macisnoob@gmail.com");
		person.setPhNo("+2348089320");
		person.setLaptop(laptop1);
		
		PersonDao personDao = new PersonDao();
		personDao.save(person); //Create
		
		List<Person> people = personDao.findAll(); //Retrieve
		for (Person prn: people) {
			//String is immutable in java.
			StringBuilder sb=new StringBuilder(); //To reduce heap size
			sb.append("Person [id="+prn.getId());
			sb.append(", name="+prn.getFullName().getFirstName());
			sb.append(" "+prn.getFullName().getLastName());
			sb.append(", email="+prn.getEmail());
			sb.append(", phNo="+prn.getPhNo());
			sb.append(", "+prn.getLaptop().getBrand());
			sb.append(", "+prn.getLaptop().getDescription()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
		
		LaptopDao laptopDao = new LaptopDao();
		laptopDao.save(laptop2); //Create
		
		Laptop updateLaptop = laptopDao.findOne(2L);
		updateLaptop.setBrand("Mac Book");
		updateLaptop.setDescription("It is just wasting your money!");
		
		laptopDao.update(updateLaptop); //Update
		
		Laptop deleteLaptop = new Laptop();
		deleteLaptop.setId(1L); //Once you get ID for an object, you can DELETE it!
		laptopDao.delete(deleteLaptop); //Delete
	}
	

	public static void easy2() {
		Laptop laptop1 = new Laptop();
		laptop1.setBrand("Dell");
		laptop1.setDescription("It is dell laptop.");
		
		Laptop laptop2 = new Laptop();
		laptop2.setBrand("System76");
		laptop2.setDescription("This is awesome!");
		
		LaptopDao laptopDao = new LaptopDao();
		laptopDao.save(laptop1); //Create
		laptopDao.save(laptop2); //Create
		
		Person person = new Person();
		person.setFullName(new FullName("Dwayne", "Johnson"));
		person.setEmail("dwaynejohnson@gmail.com");
		person.setPhNo("+1549724440");
		Laptop ltp = laptopDao.findOne(2); //select * from laptop where id=2;
		person.setLaptop(ltp);
		
		PersonDao personDao = new PersonDao();
		personDao.save(person); //Create
		
		List<Person> people = personDao.findAll(); //Retrieve
		for (Person prn: people) {
			//String is immutable in java.
			StringBuilder sb=new StringBuilder(); //To reduce heap size
			sb.append("Person [id="+prn.getId());
			sb.append(", name="+prn.getFullName().getFirstName());
			sb.append(" "+prn.getFullName().getLastName());
			sb.append(", email="+prn.getEmail());
			sb.append(", phNo="+prn.getPhNo());
			sb.append(", "+prn.getLaptop().getBrand());
			sb.append(", "+prn.getLaptop().getDescription()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
		
		/*
		Person prn = personDao.findOne(1);
		//System.out.println(prn.getLaptop().getBrand()); //This can result in LazyInitializationException
		PersonDto personDto = dependency.createPersonDto(prn); //To avoid LazyInitializationException, use DTO.
		System.out.println(personDto.getLaptop().getBrand());
		if (prn!=null) {
			personDao.delete(prn);
		}
		*/
		
		Laptop laptop = laptopDao.findOne(2); //Retrieve
		LaptopDto laptopDto = new LaptopDto(laptop); //DTO
		System.out.println(laptopDto.getBrand());
		
		if (laptop!=null) {
			laptopDao.delete(laptop); //Delete
		}
	}
	
	public static void hard1() {
		//Joining with foreign key after saving laptops!
		
		//Laptop Objects
		Laptop laptop1 = new Laptop(); laptop1.setBrand("Lenovo");
		laptop1.setDescription("It is Lenovo laptop.");
		
		Laptop laptop2 = new Laptop(); laptop2.setBrand("HP");
		laptop2.setDescription("It is HP laptop.");
		
		//Laptop Controller
		LaptopController<Laptop> laptopController = new LaptopControllerImpl();
		Laptop outputLaptop1 = laptopController.save(laptop1); //Save 
		laptopController.printDetails(outputLaptop1); //Output
		Laptop outputLaptop2 = laptopController.save(laptop2); //Save
		laptopController.printDetails(outputLaptop2);		
		
		//Retrieve
		List<Laptop> laptopList = laptopController.findAll();
		laptopController.printDetails(laptopList); //Output
		
		//Person Object
		Person person = new Person();
		person.setFullName(new FullName("Dwayne", "Johnson"));
		person.setEmail("dwaynejohnson@gmail.com");
		person.setPhNo("+1549724440");
		Laptop laptop = laptopController.findById(2); //select * from laptop where id=2;
		person.setLaptop(laptop);
		
		//Person Controller
		PersonController<Person> personController = new PersonControllerImpl();
		Person outputPerson = personController.save(person); //Save
		personController.printDetails(outputPerson); //Output
	}
	
	public static void hard2() {
		//Saving laptop with person
		
		//Laptop Objects
		Laptop laptop1 = new Laptop(); laptop1.setBrand("Lenovo");
		laptop1.setDescription("It is Lenovo laptop.");
		
		Laptop laptop2 = new Laptop(); laptop2.setBrand("HP");
		laptop2.setDescription("It is HP laptop.");
		
		//Laptop Controller
		LaptopController<Laptop> laptopController = new LaptopControllerImpl();
		Laptop outputLaptop1 = laptopController.save(laptop1); //Save 
		laptopController.printDetails(outputLaptop1); //Output
		
		//Don't save laptop 2
		//Laptop outputLaptop2 = laptopController.save(laptop2); //Save
		//laptopController.printDetails(outputLaptop2);		
		
		//Retrieve
		List<Laptop> laptopList = laptopController.findAll();
		laptopController.printDetails(laptopList); //Output
		
		//Person Object
		Person person = new Person();
		person.setFullName(new FullName("Dwayne", "Johnson"));
		person.setEmail("dwaynejohnson@gmail.com");
		person.setPhNo("+1549724440");
		person.setLaptop(laptop2); //Saving laptop along with person.
		
		//Person Controller
		PersonController<Person> personController = new PersonControllerImpl();
		Person outputPerson = personController.save(person); //Save
		personController.printDetails(outputPerson); //Output
		
		Laptop deleteLaptop = laptopController.findById(2);
		laptopController.printDetails(deleteLaptop);
		laptopController.delete(deleteLaptop); //On Delete Set Null is working here!
		
		//Wrong Update
		Laptop updateLaptop1 = laptopController.findById(1);
		updateLaptop1.setId(null); //Updating without id will insert a new data!
		updateLaptop1.setBrand("Dell");
		updateLaptop1.setDescription("This is dell XPS!");
		laptopController.update(updateLaptop1);
		
		//Update
		Laptop updateLaptop2 = laptopController.findById(3);
		updateLaptop2.setBrand("System76");
		updateLaptop2.setDescription("This is System76!");
		laptopController.update(updateLaptop2);
	}
}
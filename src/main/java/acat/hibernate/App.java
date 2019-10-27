package acat.hibernate;

import java.util.List;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dao.PersonDao;
import acat.hibernate.dependency.DependencyRegistry;
import acat.hibernate.entity.FullName;
import acat.hibernate.entity.Laptop;
import acat.hibernate.entity.Person;

public class App {
	
	public static void main( String[] args ) {
		
		DependencyRegistry dependency = DependencyRegistry.getInstance(); //Dependency
		
		Laptop laptop1 = dependency.createLaptop();
		laptop1.setBrand("Dell");
		laptop1.setDescription("It is dell laptop.");
		
		Laptop laptop2 = dependency.createLaptop();
		laptop2.setBrand("System76");
		laptop2.setDescription("This is awesome!");
		
		LaptopDao laptopDao = dependency.createLaptopDao(); //Dependency Injected
		laptopDao.save(laptop1);
		laptopDao.save(laptop2);
		
		Person person = dependency.createPerson(); //Dependency Injected
		person.setFullName(new FullName("Dwayne", "Johnson"));
		person.setEmail("dwaynejohnson@gmail.com");
		person.setPhNo("+1549724440");
		Laptop ltp = laptopDao.findOne(2); //select * from laptop where id=2;
		person.setLaptop(ltp);
		
		PersonDao personDao = dependency.createPersonDao(); //Dependency Injected
		personDao.save(person);
		
		List<Person> people = personDao.findAll();
		for (Person prn: people) {
			//String is immutable in java.
			StringBuilder sb=dependency.createStringBuilder(); //To reduce heap size
			sb.append("Person [id="+prn.getId());
			sb.append(", name="+prn.getFullName().getFirstName());
			sb.append(" "+prn.getFullName().getLastName());
			sb.append(", email="+prn.getEmail());
			sb.append(", phNo="+prn.getPhNo());
			sb.append(", "+prn.getLaptop().getBrand());
			sb.append(", "+prn.getLaptop().getDescription()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
    }
}

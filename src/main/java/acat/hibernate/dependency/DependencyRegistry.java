package acat.hibernate.dependency;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dao.PersonDao;
import acat.hibernate.entity.Laptop;
import acat.hibernate.entity.Person;

public class DependencyRegistry {

	private static DependencyRegistry instance;
	
	private DependencyRegistry() {
		
	}
	
	public static DependencyRegistry getInstance() {
		if (instance==null) {
			synchronized (DependencyRegistry.class) {
				if (instance==null) {
					instance = new DependencyRegistry();
				}
			}
		}
		return instance;
	}
	
	public Person createPerson() {
		return new Person();
	}
	
	public Laptop createLaptop() {
		return new Laptop();
	}
	
	public PersonDao createPersonDao() {
		return new PersonDao();
	}
	
	public LaptopDao createLaptopDao() {
		return new LaptopDao();
	}
	
	public StringBuilder createStringBuilder() {
		return new StringBuilder();
	}
}

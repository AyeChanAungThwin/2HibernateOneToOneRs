package acat.hibernate.dependency;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dao.PersonDao;
import acat.hibernate.dto.LaptopDto;
import acat.hibernate.dto.PersonDto;
import acat.hibernate.model.Laptop;
import acat.hibernate.model.Person;

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
	
	@SuppressWarnings("unchecked")
	public Object getInstance(Class className) {
		try {
			return className.getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

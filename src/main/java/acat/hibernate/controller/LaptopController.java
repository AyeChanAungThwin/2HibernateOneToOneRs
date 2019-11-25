package acat.hibernate.controller;

import java.util.List;

import acat.hibernate.model.Laptop;
import acat.hibernate.view.AbstractView;

public interface LaptopController<T extends Laptop> extends AbstractView<T> {

	T findById(long id);
	List<T> findAll();
	
	T save(T model);
	T update(T model);
	void delete(T model);
	void deleteById(long id);
}

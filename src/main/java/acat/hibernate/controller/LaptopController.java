package acat.hibernate.controller;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.model.Laptop;
import acat.hibernate.view.LaptopView;
import acat.hibernate.view.Status;

public class LaptopController {
	
	private Laptop model;
	private LaptopView view;

	public LaptopController(Laptop model, LaptopView view) {
		this.model = model;
		this.view = view;
	}
	
	public Laptop getLaptop() {
		return model;
	}
	
	public void setLaptop(Laptop model) {
		this.model = model;
	}
	
	public String getLaptopBrand() {
		return model.getBrand();
	}
	
	public void setLaptopBrand(String brand) {
		model.setBrand(brand);
	}
	
	public String getLaptopDescription() {
		return getLaptopDescription();
	}
	
	public void setLaptopDescription(String description) {
		model.setDescription(description);
	}
	
	public void updateView() {
		view.printLaptopDetails(model);
	}
	
	public void saveToDatabase(Status status) {
		LaptopDao laptopDao = new LaptopDao();
		boolean success = false;
		try {
			laptopDao.save(model);
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

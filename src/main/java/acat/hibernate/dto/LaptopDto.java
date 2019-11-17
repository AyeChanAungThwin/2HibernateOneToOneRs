package acat.hibernate.dto;

import java.io.Serializable;

import acat.hibernate.model.BaseEntity;
import acat.hibernate.model.Laptop;

public class LaptopDto extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2142712284820067232L;
	
	private String brand;
	private String description;
	
	public LaptopDto(Laptop laptop) {
		super.setId(laptop.getId());
		this.brand = laptop.getBrand();
		this.description = laptop.getDescription();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

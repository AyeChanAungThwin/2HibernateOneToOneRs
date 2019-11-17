package acat.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dependency.DependencyRegistry;

@Entity
@Table(name = "laptop")
public class Laptop extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2142712284820067232L;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne(mappedBy = "laptop")
	private Person person;
	
	@PreRemove
	public void onDeleteSetNullToThisForeignKeyInPerson() {
		DependencyRegistry dependency = DependencyRegistry.getInstance();
		LaptopDao dao = (LaptopDao) dependency.getInstance(LaptopDao.class);
		dao.onDeleteSetNull(Person.class, super.getId());
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

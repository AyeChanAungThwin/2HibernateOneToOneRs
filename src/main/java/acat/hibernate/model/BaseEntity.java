package acat.hibernate.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6088193042452818438L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private T id;
	
	protected BaseEntity() {
		
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}

package acat.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915317817868710134L;
	
	@Embedded
	private FullName fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phNo")
	private String phNo;
	
	/* If you use CascadeType.PERSIST here, 
	 * you don't need method under @PreRemove annotation
	 * or you don't need to set laptop=null.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "laptop_id")
	private Laptop laptop;
	
	@Column(name = "laptop_id", insertable = false, updatable = false)
	private Long laptopId; //Foreign key creation for HQL Query
	
	@PreRemove
	public void ignoreRemovingLaptopWhenDeletingAPerson() {
		laptop = null; //This method is not necessary if CascadeType is PERSIST!
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
}

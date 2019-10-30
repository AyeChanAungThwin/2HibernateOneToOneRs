# Hibernate (One to One Relationship)
## Details
> There're two entities; **Person** and **Laptop**.
> We connect tables; "person" and "laptop" with one to one relationship.
> Hibernate will auto generate the table in MySQL Server due to the addition of "<property name="hibernate.hbm2ddl.auto">update</property>" in hibernate.cfg.xml.
> If you change its value to "create", it will create a table using "DROP TABLE IS EXISTS TABLE_NAME."

## Explanation
###### Person Entity ######
-  ***In Person Entity***, we use **CascadeType.ALL** and so, when you delete a person, it will **ALSO** deletes a laptop two related to deleted person. We can avoid this using the code below.
-  Before deleting a person, we need to remove the laptop object in relation with person who's going to be deleted.
```
	@PreRemove
	public void ignoreRemovingLaptopWhenDeletingAPerson() {
		laptop = null;
	}
```
-  We want to use HQL query. Can we get the foreign key from **Person Entity**?
- Of course, you can!
-  Normally, we create the relationship like this.
```
	@OneToOne(cascade = CascadeType.ALL)
	private Laptop laptop;
```
-  And Hibernate do creates **"laptop_id"** in person table but can't access from _HQL_ but _NativeSQL_. We needed foreign key of **laptop_id** from Java code.
-  We get the foreign key by creating the following Java code.
```
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "laptop_id")
	private Laptop laptop;
	
	@Column(name = "laptop_id", insertable = false, updatable = false)
	private Long laptopId; //Foreign key creation for HQL Query
```
-  We created the new instance ***"private Long laptopId;"*** and name the column as "**laptop_id**."
-  We're not creating another field in table. We're just getting the foreign key of ***"Laptop Entity"*** and so set that column's ~~"insertable"~~ and ~~"updatable"~~ to **false**.
-  And we also need to join that column in **"private Laptop laptop;"** to inform Hibernate that we're will be getting the foreign key of **Laptop Entity** with _laptopId_;

###### Laptop Entity ######
-  Unfortunately, Hibernate doesn't support ON DELETE SET NULL mode. So, you have to create a query and update the foreign key to set null.
-  Whenever we're making a query, if we use an HQL, ***syntax of sql query*** will be _READY_ for **ANY relational database servers** like Oracle, MS SQL, MySQL, etc. That's the power of Hibernate.
-  This is Java and so let's make it ROWA if you don't want to use _NativeQuery_. But using _NativeQuery_ is a good thing because Hibernate has a problem of its own in some situations.
-  But for this, I've tested for many times in different database servers and you can use it without any problems.
-  Check **onDeleteSetNull method** I created in _AbstractDAO.java_.
-  It's just creating an HQL query; "update Person set laptopId=null where laptopId=:currentId" in that method.
-  After that, we use that method in **Laptop Entity**.
-  Before removing laptop, we updated the "laptop_id" in person to "NULL" and then remove it.
```
	@PreRemove
	public void onDeleteSetNullToThisForeignKeyInPerson() {
		DependencyRegistry dependency = DependencyRegistry.getInstance();
		LaptopDao dao = dependency.createLaptopDao();
		dao.onDeleteSetNull(Person.class, super.getId());
	}
```
###### Conclusion ######
-  We had to do like this because **CascadeType.ALL** is  messing up everything if you use or not.
-  If we use that, it deletes every rows in relations when deleting a data. For example, in this OneToOneRs, if you delete a person, laptop will be deleted. That's why we have to remove the laptop object (laptop=null;) at @PreRemove before deleting a person.
-  But if we use **CascadeType.PERSIST** in _Person Entity_, you don't need to use @PreRemove.
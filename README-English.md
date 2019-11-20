# Hibernate (One to One Relationship)
## Abstract
> You have to use **Hibernate** when we want to make CRUD operations in relational database server using Java code.
> If you have _NEVER_ done a project with the basic connectivity between **Java and MySQL Server**, I suggest you learning [this](https://www.javatpoint.com/example-to-connect-to-the-mysql-database) first.

## About
- [X] CRUD with Hibernate
- [X] Insert data using Hibernate
- [X] Fetch data using Hibernate
- [X] Update data using Hibernate
- [X] Delete data using Hibernate
- [X] ON DELETE SET NULL
- [ ] ON DELETE CASCADE

## Introduction to Hibernate
- [Press here if you're beginner to Hibernate!](https://github.com/AyeChanAungThwin/1HibernateIntroWithoutRs)

## Diagrams
- ER Diagram
<img src="images/erd.png" alt="Person with 4 attributes, ER Diagram">
- Relational Schema
<img src="images/relational.png" alt="Person with 4 attributes, Relational Schema">
- SQL Query
<table style="width:100%">
  <tr>
    <th>laptop table</th>
    <th>person table</th> 
  </tr>
  <tr>
    <td>
    <pre>
CREATE TABLE laptop
(
id BIGINT AUTO_INCREMENT NOT NULL,
brand VARCHAR(50),
description VARCHAR(255),
PRIMARY KEY (id)
);
    </pre>
    </td>
    <td>
    <pre>
CREATE TABLE person
(
id BIGINT AUTO_INCREMENT NOT NULL,
first_name VARCHAR(20),
last_name VARCHAR(20),
ph_no VARCHAR(20),
email VARCHAR(25),
laptop_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (laptop_id) REFERENCES laptop(id) ON DELETE SET NULL
);
    </pre>
    </td>
  </tr>
</table>

## Explaining Entities in Project
- We get the **One-To-One-Relationship** with the following codes.
<table style="width:100%">
  <tr>
    <th>Person Entity</th>
    <th>Laptop Entity</th> 
  </tr>
  <tr>
    <td>
    <pre>
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;
    </pre>
    </td>
    <td>
    <pre>
    @OneToOne(mappedBy = "laptop")
    private Person person;
    </pre>
    </td>
  </tr>
  <tr>
    <td>
    Creating foreign key of Laptop.
    </td>
    <td>
    "mappedBy" tells the Hibernate that the foreign key of this table is in Person Entity;
    </td>
  </tr>
</table>

- There are 5 cascade types; DETACH, MERGE, PERSIST, REFERSH and REMOVE. In here, we cascade with all of them i.e., ALL.
- It's not a good idea to use **ALL** but if we didn't use it, it gives a problem in data persistance. So, I **recommended** to use **ALL**.
- The word "cascade" means it's connected. By the time, you remove a person, it will also remove the laptop which is in relationship with that person.
- But we can fix it using @PreRemove. As by definition of its name, it removes some property before deleting a row.
```
@PreRemove
public void ignoreRemovingLaptopWhenDeletingAPerson() {
	laptop = null;
}
```
- Before deleting a person, we temporarily remove the laptop in relationship with that person by setting laptop to null.
- So, when person is removed, laptop is not removed from the table. That's how we violates the cascade type ALL. That's how we fix it!

## Creating yourself ON DELETE SET NULL with Hibernate
- We're gonna create ON DELETE SET NULL function for every relational database server. So, we have to use HQL Query for that. I'm not gonna explain my Java code here. Just try to understand it yourself. You can use it in anywhere. I'll just tell you how you can use it.
- We're gonna set the foreign key of laptop in person table to null when removing laptop. So, this is in Laptop Entity.
- Here's how it works. Before deleting a laptop, we update the foreign key of laptop to null which is in person table using @PreRemove annotation.
```
@PreRemove
public void onDeleteSetNullToThisForeignKeyInPerson() {
	DependencyRegistry dependency = DependencyRegistry.getInstance();
	LaptopDao dao = (LaptopDao) dependency.getInstance(LaptopDao.class);
	dao.onDeleteSetNull(Person.class, super.getId());
}
```
- There're 2 parameters in onDeleteSetNull method. The first parameter needs the class containing the foreign key and the second one needs the id of deletion.
- We don't execute Hibernate in Entity. But this is the only way to get it easily. Or you will have to update the foreign key id in the other table everytime before you make a deletion.

## Electronics Engineer-cum-J2EE Backend Developer ##
-  Created by - Aye Chan Aung Thwin

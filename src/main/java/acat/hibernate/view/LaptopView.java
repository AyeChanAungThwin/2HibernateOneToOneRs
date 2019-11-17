package acat.hibernate.view;

import acat.hibernate.model.Laptop;

public class LaptopView {

	public void printLaptopDetails(Laptop model) {
		StringBuilder sb = new StringBuilder();
		sb.append("Laptop [id="+model.getId());
		sb.append(", brand="+model.getBrand());
		sb.append(" "+model.getDescription()+"]");
		System.out.println(sb.toString());
	}

}

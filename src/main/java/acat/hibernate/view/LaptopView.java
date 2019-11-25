package acat.hibernate.view;

import acat.hibernate.model.Laptop;

public class LaptopView extends AbstractViewImpl<Laptop> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3409496659622457556L;

	public void printDetails(Laptop model) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("< ");
		sb.append(model.getClass().getSimpleName());
		sb.append(" Output>[id="+model.getId());
		sb.append(", brand="+model.getBrand());
		sb.append(", description="+model.getDescription()+"]\n*****");
		System.err.println(sb.toString());
	}
}

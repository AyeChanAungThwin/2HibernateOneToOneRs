package acat.hibernate.view;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractViewImpl<T extends Serializable> implements AbstractView<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1413547433261528080L;

	public void printDetails(List<T> modelList) {
		// TODO Auto-generated method stub
		for (T model: modelList) {
			printDetails(model);
		}
	}

}

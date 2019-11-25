package acat.hibernate.view;

import java.io.Serializable;
import java.util.List;

public interface AbstractView<T extends Serializable> extends Serializable {

	void printDetails(T model);
	void printDetails(List<T> modelList);
}

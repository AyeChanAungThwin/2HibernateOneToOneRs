package acat.hibernate.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import acat.hibernate.view.AbstractView;

public abstract class AbstractControllerImpl<Model extends Serializable,
											View extends AbstractView<Model>>
implements AbstractController<Model, View> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6819029925998202865L;
	private Model model;
	private View view;
	
	@SuppressWarnings("unchecked")
	public AbstractControllerImpl() {
		Class<Model> modelClass =(Class<Model>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Class<View> viewClass =(Class<View>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		
		model = (Model) getInstance(modelClass);
		view = (View) getInstance(viewClass);
	}
	
	private Object getInstance(Class<?> className) {
		try {
			return className.getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Model getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
	}
	
	public View getView() {
		// TODO Auto-generated method stub
		return view;
	}
	
	public void setView(View view) {
		// TODO Auto-generated method stub
		this.view = view;
	}
	
	public void printDetails(Model model) {
		view.printDetails(model);
	}
	
	public void printDetails(List<Model> modelList) {
		// TODO Auto-generated method stub
		view.printDetails(modelList);
	}
}

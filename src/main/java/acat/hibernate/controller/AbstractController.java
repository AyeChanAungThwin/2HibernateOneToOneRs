package acat.hibernate.controller;

import java.io.Serializable;

import acat.hibernate.view.AbstractView;

public interface AbstractController<Model extends Serializable,
									View extends AbstractView<Model>> extends AbstractView<Model>{
	
	Model getModel();
	void setModel(Model model);
	
	View getView();
	void setView(View view);
}

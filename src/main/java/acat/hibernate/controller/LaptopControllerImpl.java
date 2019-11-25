package acat.hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import acat.hibernate.dto.LaptopDto;
import acat.hibernate.model.Laptop;
import acat.hibernate.service.LaptopService;
import acat.hibernate.service.LaptopServiceImpl;
import acat.hibernate.view.LaptopView;

public class LaptopControllerImpl extends AbstractControllerImpl<Laptop, LaptopView> implements LaptopController<Laptop> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1738568176754123010L;
	private final LaptopService service = new LaptopServiceImpl();
	
	@Override
	public Laptop getModel() {
		// TODO Auto-generated method stub
		return super.getModel();
	}

	@Override
	public void setModel(Laptop model) {
		// TODO Auto-generated method stub
		super.setModel(model);
	}

	@Override
	public LaptopView getView() {
		// TODO Auto-generated method stub
		return super.getView();
	}

	@Override
	public void setView(LaptopView view) {
		// TODO Auto-generated method stub
		super.setView(view);
	}

	@Override
	public void printDetails(Laptop model) {
		// TODO Auto-generated method stub
		super.printDetails(model);
	}

	@Override
	public void printDetails(List<Laptop> modelList) {
		// TODO Auto-generated method stub
		super.printDetails(modelList);
	}

	public Laptop findById(long id) {
		// TODO Auto-generated method stub
		LaptopDto dto = service.findOne(id);
		return dto.getEntity();
	}

	public List<Laptop> findAll() {
		// TODO Auto-generated method stub
		List<LaptopDto> dtoList = service.findAll();
		List<Laptop> laptopList = null;
		for (LaptopDto dto: dtoList) {
			if (laptopList==null) {
				laptopList = new ArrayList<Laptop>();
			}
			laptopList.add(dto.getEntity());
		}
		return laptopList;
	}

	public Laptop save(Laptop model) {
		// TODO Auto-generated method stub
		LaptopDto dto = service.save(new LaptopDto(model));
		return dto.getEntity();
	}

	public Laptop update(Laptop model) {
		// TODO Auto-generated method stub
		LaptopDto dto = service.update(new LaptopDto(model));
		return dto.getEntity();
	}

	public void delete(Laptop model) {
		// TODO Auto-generated method stub
		service.delete(new LaptopDto(model));
	}

	public void deleteById(long id) {
		// TODO Auto-generated method stub
		service.deleteById(id);
	}
}
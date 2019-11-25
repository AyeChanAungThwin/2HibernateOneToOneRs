package acat.hibernate.dao;

import java.util.List;

import acat.hibernate.model.Laptop;
import acat.hibernate.repository.LaptopRepository;

public class LaptopDao extends AbstractDaoImpl<Laptop> implements LaptopRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6835758574835961459L;

	public LaptopDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Laptop findOne(long id) {
		// TODO Auto-generated method stub
		return super.findOne(id);
	}

	@Override
	public List<Laptop> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public Laptop save(Laptop entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

	@Override
	public Laptop update(Laptop entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public void delete(Laptop entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		super.deleteById(entityId);
	}
}

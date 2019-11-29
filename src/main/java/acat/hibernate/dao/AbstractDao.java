package acat.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<Entity extends Serializable, ID extends Comparable<ID> & Serializable> {
	
	Entity findOne(ID id);
	
	List<Entity> findAll();
	
	Entity save(Entity entity);
	
	Entity update(Entity entity);
	
	void delete(Entity entity);
	
	void deleteById(ID entityId);
}

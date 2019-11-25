package acat.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import acat.hibernate.dependency.DependencyRegistry;
import acat.hibernate.hibernateutils.HibernateUtils;

public abstract class AbstractDaoImpl<T extends Serializable> implements AbstractDao<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4106797951677472481L;
	private Class<T> entityName = null;
	private Session session = null;
	private Transaction tx = null;
	
	@SuppressWarnings("unchecked")
	public AbstractDaoImpl() {
		this.entityName =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T findOne(long id) {
		T entity = null;
		try {
			startOperation();
			entity = session.get(entityName, id);
			tx.commit();
		}
		catch (HibernateException e) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> data = null;
		try {
			startOperation();
			TypedQuery<T> query = session.createQuery("from "+entityName.getName());
			data = query.getResultList();
			tx.commit();
		}
		catch (HibernateException e) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		return data;
	}
	
	public T save(T entity) {
		try {
			startOperation();
			session.saveOrUpdate(entity);
			tx.commit();
		}
		catch (HibernateException e) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public T update(T entity) {
		try {
			startOperation();
			entity = (T) session.merge(entity);
			tx.commit();
		}
		catch (HibernateException e) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		return entity;
	}
	
	public void delete(T entity) {
		try {
			startOperation();
			session.delete(entity);
			tx.commit();
		}
		catch (HibernateException e) {
			tx.rollback();
		}
		finally {
			session.close();
		}
	}
	
	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public void onDeleteSetNull(Class<?> otherEntity, Long id) {
		try {
			startOperation();
			DependencyRegistry dependency = DependencyRegistry.getInstance();
			StringBuilder sb = (StringBuilder) dependency.getInstance(StringBuilder.class);
			sb.append("update ");
			sb.append(otherEntity.getSimpleName());
			sb.append(" set ");
			sb.append(entityName.getSimpleName().toLowerCase());
			sb.append("Id=null where ");
			sb.append(entityName.getSimpleName().toLowerCase());
			sb.append("Id=:current_id");
			String hql = sb.toString();
			//update Person from laptopId=null where laptopId=:?
			TypedQuery<T> query = session.createQuery(hql);
			query.setParameter("current_id", id);
			int result = query.executeUpdate();
			System.out.println("Result: "+result);
			tx.commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	private void startOperation() {
		session = HibernateUtils.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
}

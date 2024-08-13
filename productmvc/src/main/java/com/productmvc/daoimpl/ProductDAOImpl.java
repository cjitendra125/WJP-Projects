package com.productmvc.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.productmvc.dao.ProductDAO;
import com.productmvc.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer save(Product product) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		Integer result = (Integer) session.save(product);
		txn.commit();
		return result;
	}

	@Override
	public List<Product> getAll() {
		Session session = sessionFactory.openSession();
		List<Product> products = session.createQuery("FROM Product").list();
		return products;
	}

	@Override
	public Product getById(int id) {
		Session session = sessionFactory.openSession();
		return session.get(Product.class, id);
	}

	@Override
	public void remove(int id) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		Product product = session.get(Product.class, id);
		session.delete(product);
		txn.commit();
		
	}

}
